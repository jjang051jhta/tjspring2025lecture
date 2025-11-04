package com.jjang051.security.service;

import com.jjang051.security.dto.SignupDto;
import com.jjang051.security.entity.Member;
import com.jjang051.security.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member saveMember(SignupDto signupDto){

        memberRepository.save(signupDto);
    }
}
