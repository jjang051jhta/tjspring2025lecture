package com.jjang051.jpa.service;

import com.jjang051.jpa.entity.Member;
import com.jjang051.jpa.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AutoClose;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {
    @Autowired
    MemberRepository memberRepository;


    @Test
    void save() {
        Member member = Member.builder()
                .userID("hong4")
                .userPW("1234")
                .userName("홍길동")
                .zipcode("12345")
                .userAddress("경기도 일산시 장항동 11")
                .userEmail("hong4@daum.net")
                .build();
        Member insertedMember = memberRepository.save(member);
        Assertions.assertNotNull(insertedMember);
    }
}