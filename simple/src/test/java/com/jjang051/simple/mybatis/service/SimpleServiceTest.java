package com.jjang051.simple.mybatis.service;

import com.jjang051.simple.mybatis.dto.SimpleDto;
//import org.junit.jupiter.api.Assertions;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SimpleServiceTest {

    @Autowired
    SimpleService simpleService;

    @Test
    void findAll() {
        List<SimpleDto> list = simpleService.findAll();
        System.out.println("조회된 데이터 갯수 : "+list.size());
        assertThat(list.size()).isGreaterThan(0);
    }

    @Test
    void test01() {
        int result = 100+10;
        assertThat(result)
                .isPositive()
                .isEqualTo(110)
                .isGreaterThan(10);
    }
}