package com.jjang051.security.dto;

import com.jjang051.security.constant.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    @NotBlank(message = "아이디는 필수입력사항입니다.")
    private String userID;
    @NotBlank(message = "패스워드확인해 주세요")
    private String userPW;
}
