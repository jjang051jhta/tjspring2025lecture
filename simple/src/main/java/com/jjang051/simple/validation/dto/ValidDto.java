package com.jjang051.simple.validation.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ValidDto {
    @NotBlank(message = "이름은 필수입력사항입니다.")
    private String userName;

    @NotBlank(message = "이메일은 필수입력사항입니다.")
    @Email(message = "이메일 형식에 맞게 써주세요")
    private String userEmail;

    @NotBlank(message = "이메일은 필수입력사항입니다.")
    @Size(min = 4, max = 20)
    private String userPW;

}
