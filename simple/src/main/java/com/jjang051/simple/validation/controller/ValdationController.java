package com.jjang051.simple.validation.controller;

import com.jjang051.simple.validation.dto.EventDto;
import com.jjang051.simple.validation.dto.WriteDto;
import com.jjang051.simple.validation.dto.ValidDto;
import jakarta.validation.Valid;
import jdk.jfr.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/valid")
@Slf4j
public class ValdationController {
    @GetMapping("/valid01")
    public String valid01(@ModelAttribute(name = "validDto") ValidDto validDto,
                          Model model) {
        model.addAttribute("validDto", new ValidDto());
        //log.info("validDto = {}", validDto);
        return "valid/valid01";
    }
    @PostMapping("/valid01")
    //@ResponseBody
    public String valid01Process(@Valid
                            @ModelAttribute(name = "validDto") ValidDto validDto,
                                 BindingResult bindingResult) {
        log.info("validDto = {}", validDto);
        boolean passwordConfirm = false;
        boolean emailConfirm = false;
        boolean isAgree = false;

        if(passwordConfirm==false){
            bindingResult.reject("passwordMismatch","비밀번호 확인해 주세요");
        }
        if(emailConfirm==false){
            bindingResult.reject("emailMismatch","이메일이 일치하지 않습니다.");
        }
        if(isAgree==false){
            //global error
            bindingResult.reject("isAgree","이용약관에 체크해주세요.");
        }
        if(validDto.getUserEmail().equals("jjang051@hanmail.net")) {
            bindingResult.rejectValue("userEmail","duplicate","사용중인 이메일입니다.");

        }
        if(bindingResult.hasErrors()){
            return "valid/valid01";
        }
        return "/";
    }

    @GetMapping("/write")
    public String write(Model model) {
        model.addAttribute("writeDto", new WriteDto());
        return "valid/write";
    }
    @PostMapping("/write")
    public String writeProcess(@Valid @ModelAttribute("writeDto") WriteDto writeDto,
                               BindingResult bindingResult, Model model) {
        log.info("writeDto = {}", writeDto);
        if(bindingResult.hasErrors()){
            return "valid/write";
        }
        return "redirect:/";
    }
    @GetMapping("/event")
    public String event(Model model) {
        model.addAttribute("eventDto", new EventDto());
        return "valid/event";
    }
    @PostMapping("/event")
    public String eventProcess(@Valid @ModelAttribute("eventDto") EventDto eventDto,
                               BindingResult bindingResult,
                               Model model) {
        log.info("eventDto = {}", eventDto);
        if(eventDto.getEndDate().isBefore(eventDto.getStartDate())){
            bindingResult.reject("notValid","종료일이 시작일보다 앞설 수 없습니다.");
        }
        if(bindingResult.hasErrors()){
            return "valid/event";
        }
        return "redirect:/";
    }
}
