package com.jjang051.simple.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SignupDto {
    private String userID;
    private String password;
    private String userName;
    private String zipcode;
}
