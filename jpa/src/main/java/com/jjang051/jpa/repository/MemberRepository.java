package com.jjang051.jpa.repository;

import com.jjang051.jpa.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Integer> {
    Optional<Member> findByUserID(String userID);
    Optional<Member> findByUserEmail(String userEmail);
}
