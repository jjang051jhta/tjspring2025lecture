package com.jjang051.security.dto;

import com.jjang051.security.constant.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignupDto {
    private Long id;
    @NotBlank(message = "아이디는 필수입력사항입니다.")
    private String userID;
    private String userName;
    @NotBlank(message = "패스워드는 필수입력사항입니다.")
    private String userPW;
    @NotBlank(message = "패스워드확인해 주세요")
    private String userPWConfirm;
    @NotBlank(message = "아이디는 필수입력사항입니다.")
    @Email(message = "이메일형식에 맞게 적어주세요.")
    private String userEmail;
    private String userPhone;
    private String address01;
    private String address02;
    private String address03;
    private String zipcode;
    private MultipartFile profile;
    @Enumerated(EnumType.STRING)
    private Role role; //ROLE_ADMIN, ROLE_MANAGER, ROLE_USER
}
