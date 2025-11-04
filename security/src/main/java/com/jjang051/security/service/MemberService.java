package com.jjang051.security.service;

import com.jjang051.security.constant.Role;
import com.jjang051.security.dto.SignupDto;
import com.jjang051.security.entity.Member;
import com.jjang051.security.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    public Member saveMember(SignupDto signupDto){
        Member signupMember = Member.builder()
                .userID(signupDto.getUserID())
                .userName(signupDto.getUserName())
                .userPW(passwordEncoder.encode(signupDto.getUserPW())) //암호화해서 넣지 않으면 security가 검증 못함
                .userEmail(signupDto.getUserEmail())
                .userPhone(signupDto.getUserPhone())
                .zipcode(signupDto.getZipcode())
                .profile(signupDto.getProfile().getOriginalFilename())
                .userAddress(signupDto.getAddress01()+" "+signupDto.getAddress02()+" "+signupDto.getAddress03())
                .role(Role.ROLE_USER)
                .build();
        return memberRepository.save(signupMember);
    }
}
