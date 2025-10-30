package com.jjang051.jpa.service;

import com.jjang051.jpa.constant.Role;
import com.jjang051.jpa.dto.MemberDto;
import com.jjang051.jpa.entity.Member;
import com.jjang051.jpa.repository.MemberRepository;
import com.jjang051.jpa.utils.DateTimeRenameStrategy;
import com.jjang051.jpa.utils.FileRenameStrategy;
import com.jjang051.jpa.utils.UUIDRenameStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MemberRepository memberRepository;

    @Value("${file.path}")
    private String upload;


    public Member save(MemberDto memberDto) {
        //여기다가 dto -> entity로 바꿔주는 로직을 작성
        String originalFileName = memberDto.getProfile().getOriginalFilename();
        System.out.println("originalFileName: " + originalFileName);
        String renameFile = originalFileName;
        if (originalFileName != null && !originalFileName.isEmpty()) {
            FileRenameStrategy fileRenameStrategy = new UUIDRenameStrategy();
            renameFile = fileRenameStrategy.rename(originalFileName);
            Path path = Paths.get(upload + renameFile);
            try {
                Files.write(path,memberDto.getProfile().getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        Member member = Member.builder()
                .userID(memberDto.getUserID())
                .userName(memberDto.getUserName())
                .userEmail(memberDto.getUserEmail())
                .zipcode(memberDto.getZipcode())
                .address(memberDto.getAddress01()+" / "+memberDto.getAddress02()+" / "+memberDto.getAddress03())
                .userPW(bCryptPasswordEncoder.encode(memberDto.getUserPW()))
                .profile(memberDto.getProfile().getOriginalFilename())
                .renameProfile(renameFile)
                .role(Role.ROLE_USER)
                .build();
        Member insertedMember = memberRepository.save(member);
        //구현체가 없음 개발자가 따로구현을 하지 않아도 proxy pattern방식을 통해 구현체 하나를 넣어준다.
        //SimpleJpaRepository가 진짜 구현체임....
        return insertedMember;
    }

    public String findByUserEmail(String userEmail) {
        Optional<Member> optionalMember = memberRepository.findByUserEmail(userEmail);
        if(optionalMember.isPresent()){
            Member member = optionalMember.get();
            return member.getUserID();
        } else return null;
    }

    @Transactional
    //dirty checking
    public void changePassword(String randomNum, String userEmail) {
        Optional<Member> optionalMember = memberRepository.findByUserEmail(userEmail);
        if(optionalMember.isPresent()){
            System.out.println("optionalMember02: " + optionalMember.get());
            Member findedMember = optionalMember.get();
            findedMember.changeUserPW(bCryptPasswordEncoder.encode(randomNum));
        }
    }

    @Transactional
    public void resetPassword(String userID, String currentPassword, String newPassword) {
        Member findedMember = memberRepository.findByUserID(userID).orElse(null);
        if(!bCryptPasswordEncoder.matches(currentPassword,findedMember.getUserPW())) {
            throw new IllegalArgumentException("현재 비밀번호가 올바르지 않습니다.");
        }
        if(bCryptPasswordEncoder.matches( newPassword,findedMember.getUserPW())) {
            throw new IllegalArgumentException("이전과 같은 비밀번호는 사용할 수 없습니다.");
        }
        findedMember.changeUserPW(bCryptPasswordEncoder.encode(newPassword));
    }
}
