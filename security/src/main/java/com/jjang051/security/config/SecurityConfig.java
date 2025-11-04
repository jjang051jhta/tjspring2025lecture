package com.jjang051.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth ->
                auth
                        .requestMatchers("/",
                                    "/main",
                                    "/index",
                                    "/member/signup",
                                    "/member/login",
                                    "/board/list",
                                    "/css/**","/js/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form ->
                        form
                                .loginPage("/member/login") //get방식으로 로그인 폼 보여주는 곳
                                .loginProcessingUrl("/member/login") // post로 처리되는 곳
                                .defaultSuccessUrl("/",true)
                                .failureUrl("/member/login?error=true")
                                .usernameParameter("userID") //form안에 name값이 userID
                                .passwordParameter("userPW") //form안에 패스워드 항목의 name값이 userPW
                                .permitAll()
                )
                .logout(logout ->
                        logout
                                .logoutUrl("/member/logout")
                                .deleteCookies("JSESSIONID")
                                .invalidateHttpSession(true)
                                .logoutSuccessUrl("/")
                        );
        return http.build();
    }
}
