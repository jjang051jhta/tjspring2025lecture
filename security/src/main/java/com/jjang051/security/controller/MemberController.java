package com.jjang051.security.controller;

import com.jjang051.security.dto.SignupDto;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class MemberController {
    @GetMapping("/member/login")
    public String login(){
        return "member/login";
    }
    @GetMapping("/member/signup")
    public String signup(Model model){
        model.addAttribute("signupDto", new SignupDto());
        return "member/signup";
    }
    @PostMapping("/member/signup")
    public String signupProcess(@Valid @ModelAttribute(name = "signupDto") SignupDto signupDto,
                                BindingResult bindingResult){
        log.info("signDto==={}",signupDto);
        return "member/signup";
    }


    @GetMapping("/")
    public String home(){
        return "index/index";
    }
}
