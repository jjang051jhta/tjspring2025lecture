package com.jjang051.simple.mybatis.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
public class SimpleDto {
    private int id ;
    private String writer ;
    private String title;
    private String content;
    private LocalDateTime regdate;
    private  int hit;
    private String password;
}
