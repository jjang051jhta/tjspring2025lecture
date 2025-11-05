package com.jjang051.security.service;

import com.jjang051.security.constant.Role;
import com.jjang051.security.dto.SignupDto;
import com.jjang051.security.entity.Member;
import com.jjang051.security.repository.MemberRepository;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${file.path}")
    private String upload;

    public Member saveMember(SignupDto signupDto){
        //DB에 파일 저장 안됨
        // 특정 경로에 파일을 업로드 하고 그 경로를 DB에 저장
        try {
            Files.createDirectories(Path.of(upload));
        } catch (IOException e) {
            throw new RuntimeException("업로드 폴더 생성 실패");
        }

        String originalFileName = null;  //원본 파일
        String renameFileName = null;   //이름 바꿔서 저장
        String renameThumbnailFileName = null;  // 썸네일
        MultipartFile profile = signupDto.getProfile();
        if(profile!=null && !profile.isEmpty()){
            //파일 처리
            try {
                //1. 확장자 분리
                //2. 파일명과 확장자 사이에 날짜 넣어서 리네임으로조합
                originalFileName = profile.getOriginalFilename(); //profile.jpg

                int dot = originalFileName.lastIndexOf(".");
                String fileName = originalFileName.substring(0, dot);
                String extension = originalFileName.substring(dot+1);
                log.info("fileName==={}, extension==={}", fileName, extension);
                String uuid = UUID.randomUUID().toString();
                renameFileName = fileName+"_"+uuid+"."+extension; //profile_fhdjshfkhjdsfdhsjfk.jpg
                renameThumbnailFileName = fileName+"_"+uuid+"_thumb."+extension; //profile_fhdjshfkhjdsfdhsjfk_thumb.jpg
                Path path = Path.of(upload, renameFileName);
                Path thumbnailPath = Path.of(upload, renameThumbnailFileName);
                //Files.write(path,profile.getBytes()); //파일을 쓰는거긴 한데  메모리에 올려서 쓴다. 파일용량이 크면 속도가 느리다.
                profile.transferTo(path.toFile()); //메모리에 올리지 않는다.
                Thumbnails.of(path.toFile())
                                .useExifOrientation(true)
                                .size(300, 300)
                                .crop(Positions.CENTER)
                                .keepAspectRatio(true)
                                .toFile(thumbnailPath.toFile());


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        Member signupMember = Member.builder()
                .userID(signupDto.getUserID())
                .userName(signupDto.getUserName())
                .userPW(passwordEncoder.encode(signupDto.getUserPW())) //암호화해서 넣지 않으면 security가 검증 못함
                .userEmail(signupDto.getUserEmail())
                .userPhone(signupDto.getUserPhone())
                .zipcode(signupDto.getZipcode())
                .profile(renameFileName)
                .renameProfile(renameThumbnailFileName)
                .userAddress(signupDto.getAddress01()+" "+signupDto.getAddress02()+" "+signupDto.getAddress03())
                .role(Role.ROLE_USER)
                .build();
        return memberRepository.save(signupMember);
    }

    public Boolean idCheck(String userID) {
        return memberRepository.existsByUserID(userID);
    }
    public Boolean emailCheck(String userEmail) {
        return memberRepository.existsByUserEmail(userEmail);
    }

    @Transactional
    public boolean deleteMember(String userID,String userPW) {
        Member member = memberRepository.findByUserID(userID).orElse(null);
        if(member==null){
            return false;
        }
        if(!passwordEncoder.matches(userPW, member.getUserPW())) {
            return false;
        }
        String profile = member.getProfile();
        String thumbnail = member.getRenameProfile();
        memberRepository.delete(member);
        Path profilePath = Path.of(upload, profile);
        Path thumbnailPath = Path.of(upload, thumbnail);
        try {
            Files.deleteIfExists(profilePath);
            Files.deleteIfExists(thumbnailPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

}
