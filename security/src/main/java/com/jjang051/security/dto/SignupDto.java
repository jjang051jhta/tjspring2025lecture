package com.jjang051.security.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupDto {
    private String userID;
    private String userName;
    private String userPW;
    private String userPWConfirm;
    private String userEmail;
    private String userPhone;
    private String address01;
    private String address02;
    private String address03;
    private String profile;


}
