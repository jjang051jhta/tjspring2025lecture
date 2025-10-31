package com.jjang051.simple.validation.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class EventDto {
    @NotBlank(message = "필수입력사항입니다.")
    private String title;

    @NotBlank(message = "필수입력사항입니다.")
    @Size(max=30,message = "30글자 이내로 써주세요")
    private String content;

    @AssertTrue(message = "약관에 동의해주세요.")
    private boolean agree;

    @NotNull(message = "시작일은 필수입력사항입니다.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @NotNull(message = "종료일은 필수입력사항입니다.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
}
