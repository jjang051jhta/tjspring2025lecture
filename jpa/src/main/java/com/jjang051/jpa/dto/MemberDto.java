package com.jjang051.jpa.dto;

import com.jjang051.jpa.constant.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDto {
    private int id;
    @NotBlank(message = "아이디는 필수입력사항입니다.")
    private String userID;
    @Size(min=4,message = "최소 4글자 이상입니다.")
    @NotBlank(message = "패스워드는 필수입력사항입니다.")
    private String userPW;
    @NotBlank(message = "이름은 필수입력사항입니다.")

    @NotBlank(message = "패스워드확인는 필수입력사항입니다.")
    private String userPWConfirm;
    private String userName;
    @NotBlank(message = "이메일은 필수입력사항입니다.")
    @Email(message = "이메일형식에 맞게 써주세요.")
    private String userEmail;

    private String address01;

    private String address02;

    private String address03;

    private String zipcode;

    private MultipartFile profile;  // db table 컬럼에 이미지를 파일째 보관하는 방법은 없다. 이미지의 경로만 넣어둔다.

    @Enumerated(EnumType.STRING)
    private Role role; //username,password, role  권한  admin,manager, user
}
