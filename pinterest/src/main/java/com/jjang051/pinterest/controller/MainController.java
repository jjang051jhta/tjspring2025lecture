package com.jjang051.pinterest.controller;

import com.jjang051.pinterest.dto.ImgDto;
import com.jjang051.pinterest.entity.ImgEntity;
import com.jjang051.pinterest.service.ImageService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final ImageService imageService;
    @GetMapping({"/main","/index","/"})
    public String main(){
        return "html/index";
    }
    @GetMapping("/data")
    @ResponseBody
    public Map<String,List<ImgEntity>> data(){
        List<ImgEntity> list = imageService.findAll();
        Map<String,List<ImgEntity>> map = new HashMap<>();
        map.put("typoList",list);
        return map;
    }
}
