package com.jjang051.security.controller;

import com.jjang051.security.dto.LoginDto;
import com.jjang051.security.dto.SignupDto;
import com.jjang051.security.entity.Member;
import com.jjang051.security.repository.MemberRepository;
import com.jjang051.security.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
@RequiredArgsConstructor

public class MemberController {
    private final MemberService memberService;

    @GetMapping("/member/login")
    public String login(Model model) {
        model.addAttribute("loginDto", new LoginDto());
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
        if(bindingResult.hasErrors()){
            return "member/signup";
        }
        Member insertedMember = memberService.saveMember(signupDto);
        log.info("insertedMember==={}",insertedMember);
        return "redirect:/";
    }


    @GetMapping("/")
    public String home(){
        return "index/index";
    }

    @GetMapping({"/admin", "/admin/", "/admin/index"})
    public String admin() {
        return "admin/index"; // templates/admin/index.html
    }
    @GetMapping("/error/403")
    public String error403() {
        return "error/403"; // templates/admin/index.html
    }


}
