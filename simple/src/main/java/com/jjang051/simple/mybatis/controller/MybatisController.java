package com.jjang051.simple.mybatis.controller;

import com.jjang051.simple.mybatis.dao.SimpleDao;
import com.jjang051.simple.mybatis.dto.SimpleDto;
import com.jjang051.simple.mybatis.service.SimpleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MybatisController {
    //front -> controller -> service -> dao -> db
    private final SimpleService simpleService;

    @GetMapping("/mybatis/list")
    public String list(Model model){
        List<SimpleDto> list = simpleService.findAll();
        model.addAttribute("list",list);
        return "mybatis/list";
    }
}
