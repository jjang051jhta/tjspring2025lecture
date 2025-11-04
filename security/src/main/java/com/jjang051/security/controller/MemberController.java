package com.jjang051.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {
    @GetMapping("/member/login")
    public String login(){
        return "member/login";
    }
    @GetMapping("/")
    public String home(){
        return "index/index";
    }
}
