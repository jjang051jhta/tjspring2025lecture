package com.jjang051.jpa.config;

import com.jjang051.jpa.service.OAuth2DetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final OAuth2DetailsService oAuth2DetailsService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth ->
                auth
                    .requestMatchers(
                                    "/",
                                    "/main",
                                    "/index",
                                    "/member/signup",
                                    "/member/login",
                                    "/member/find-password",
                                    "/member/find-id",
                                    "/member/mail",
                                    "/css/**","/js/**").permitAll()
                    .requestMatchers("/admin/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
                )
                //내 도메인 내에서만 접속 허용
                .csrf(csrf -> csrf.disable())
                .formLogin(form ->
                                form.loginPage("/member/login")
                                    .loginProcessingUrl("/member/login")  //post
                                    .defaultSuccessUrl("/",true)
                                    .usernameParameter("userID")  //username
                                    .passwordParameter("userPW")  //password
                                    .failureUrl("/member/login?error=true")
                                    .permitAll()
                )
                .oauth2Login(oauth2 -> {
                    oauth2.loginPage("/member/login")
                            .defaultSuccessUrl("/",true)
                            .userInfoEndpoint(userInfo -> {
                                userInfo.userService(oAuth2DetailsService);
                            });
                })
                .logout(logout ->
                        logout.logoutUrl("/member/logout")
                              .logoutSuccessUrl("/"));

                return http.build();
    };
}
