package com.jjang051.simple.validation.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WriteDto {

    @NotBlank(message = "제목은 필수입력사항입니다.")
    private String title;
    @NotBlank(message = "내용은 필수입력사항입니다.")
    @Size(max=300, message = "내용은 300자 이하로 써주세요.")
    private String content;
    @AssertTrue(message = "약관에 동의해주세요.")
    private boolean agree;
}
