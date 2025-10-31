package com.jjang051.simple.validation.controller;

import com.jjang051.simple.validation.dto.ValidDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/valid")
@Slf4j
public class ValdationController {
    @GetMapping("/valid01")
    public String valid01(@ModelAttribute(name = "validDto") ValidDto validDto) {
        //log.info("validDto = {}", validDto);
        return "valid/valid01";
    }
    @PostMapping("/valid01")
    //@ResponseBody
    public String valid01Process(@ModelAttribute(name = "validDto") ValidDto validDto) {
        log.info("validDto = {}", validDto);
        if(validDto.getUserEmail().isBlank()){
            return "valid/valid01";
        }
        return "/";
    }
}
