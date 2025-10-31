package com.jjang051.simple.controller;

import com.jjang051.simple.dto.MemberDto;
import com.jjang051.simple.dto.RequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
public class HomeController {
    @GetMapping("/member/login")
    public String login(@RequestParam(name = "user-name",required = false, defaultValue = "")
                            String userName,
                        @RequestParam(name = "user-email",required = false, defaultValue = "")
                        String userEmail,
                        @RequestParam(name = "user-age",required = false, defaultValue = "0")
                            int userAge
                        ){
        log.info("userName={}, userEmail={}, userAge={}", userName, userEmail, userAge);
        if(userName.equals("장성호")){
            return "member/login02";
        } else {
            return "member/login";
        }
    }
    @GetMapping("/member/login02")
    @ResponseBody
    public Map<String,String> login02(){
        Map<String,String> map = new HashMap<>();
        map.put("code","200");
        return map;
    }

    @GetMapping("/member/login03")
    @ResponseBody
    public String login03(@ModelAttribute("memberDto") MemberDto memberDto){
        log.info("userName={}", memberDto.getUserName());
        log.info("userEmail={}", memberDto.getUserEmail());
        log.info("userAge={}", memberDto.getUserAge());
        return "ModelAttribute";
    }
    @GetMapping("/member/login04/{userName}")
    @ResponseBody
    public String login04(@PathVariable("userName") String userName){
        log.info("userName={}", userName);
        return "pathvariable";
    }
    @PostMapping("/request")
    @ResponseBody
    public String request(@RequestParam(name = "req01", required = true, defaultValue = "") String req01,
                          @RequestParam(name = "req02", required = true, defaultValue = "") String req02,
                          @RequestParam(name = "req03", required = true, defaultValue = "") String req03
                          ){
        log.info("req01={}", req01);
        log.info("req02={}", req02);
        log.info("req03={}", req03);
        return "success";
    }
    @PostMapping("/request02")
    //@ResponseBody
    public String request(@ModelAttribute(name = "req") RequestDto requestDto,
                          Model model){
        log.info("requestDto={}", requestDto);
        requestDto.setReq01(requestDto.getReq01()+10);
        requestDto.setReq02(requestDto.getReq02()+10);
        requestDto.setReq03(requestDto.getReq03()+10);
        //model.addAttribute("req", requestDto);
        return "request/request02";
    }
    @PostMapping("/request03/{userID}")
    @ResponseBody
    public String requestPath(@PathVariable(value="userID",required = true) String userID){
        log.info("userID={}", userID);
        return "나의 아이디는 = "+userID;
        http://localhost:8080/member/detail/jjang051?type=admin

    }
}
