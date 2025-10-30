package com.jjang051.jpa.entity;

import com.jjang051.jpa.constant.Role;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

@Entity
//@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String userID;

    private String userName;

    @Column(nullable = false)
    private String userPW;
    @Column(unique = true)
    private String userEmail;

    private String address;

    private String zipcode;

    private String profile;  // db table 컬럼에 이미지를 파일째 보관하는 방법은 없다. 이미지의 경로만 넣어둔다.

    private String renameProfile;  // db table 컬럼에 이미지를 파일째 보관하는 방법은 없다. 이미지의 경로만 넣어둔다.

    @Enumerated(EnumType.STRING)
    private Role role; //username,password, role  권한  admin,manager, user

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "writer")
    //내쪽에 컬럼을 만들지 마라 연관관계의 주인은 board이다.
    private List<Board> board;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "writer")
    //내쪽에 컬럼을 만들지 마라 연관관계의 주인은 board이다.
    private List<Comment> comments;


    public void changeUserPW(String encodedUserPW) {
        this.userPW = encodedUserPW;
    }
}
