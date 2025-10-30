package com.jjang051.jpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class PasswordEncoderConfig {
    //Spring Container에 등록됨 이말인즉슨  사용자가 new 를 통해 생성하지 않아도 된다. 자동 주입된다.
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
      return new BCryptPasswordEncoder();
    }
}
