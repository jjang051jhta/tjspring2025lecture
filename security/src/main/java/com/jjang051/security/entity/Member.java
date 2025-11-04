package com.jjang051.security.entity;

import jakarta.persistence.*;

@Entity
@SequenceGenerator(
        name="BOARD_SEQ_GENERATOR",
        sequenceName = "board_jpa_seq01",
        initialValue = 1,
        allocationSize = 1
)
@Table(name = "member_jpa")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "BOARD_SEQ_GENERATOR")
    //단점이 1씩 증가하지 않음..5개씩 증가
    private Long id;
    private String userName;
    private String userPW;
    private String role;
    @Column(unique = true, nullable = false)
    private String userID;

    @Column(unique = true)
    private String userEmail;
    private String userPhone;
    private String userAddress;
    private String zipcode;
    private String profile;
    private String renameProfile;



}
