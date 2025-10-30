package com.jjang051.jpa.admin;

import com.jjang051.jpa.constant.Role;
import com.jjang051.jpa.entity.Member;
import com.jjang051.jpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AdminInitializer implements CommandLineRunner {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MemberRepository memberRepository;
    //서버시작 전에 해야할 실행문
    @Override
    public void run(String... args) throws Exception {
        Optional<Member> optionalMember = memberRepository.findByUserID("admin");
        if(!optionalMember.isPresent()){
            Member member = Member.builder()
                    .userID("admin")
                    .userName("최고관리자")
                    .userEmail("jjang051@hanmail.net")
                    .userPW(bCryptPasswordEncoder.encode("1234"))
                    .role(Role.ROLE_ADMIN)
                    .build();
            memberRepository.save(member);
        } else {
            System.out.println("관리자 계정이 이미 있습니다.");
        }
        Optional<Member> optionalMember02 = memberRepository.findByUserID("jjang051");
        if(!optionalMember02.isPresent()){
            Member member = Member.builder()
                    .userID("jjang051")
                    .userName("장성호")
                    .userEmail("jjang052@hamail.net")
                    .userPW(bCryptPasswordEncoder.encode("1234"))
                    .role(Role.ROLE_USER)
                    .build();
            memberRepository.save(member);
        } else {
            System.out.println("관리자 계정이 이미 있습니다.");
        }
    }
}
