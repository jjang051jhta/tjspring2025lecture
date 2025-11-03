package com.jjang051.simple.mybatis.service;

import com.jjang051.simple.mybatis.dto.SimpleDto;
//import org.junit.jupiter.api.Assertions;
import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
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

    @Test
    //@Rollback(false)
    void writeTest() {
        SimpleDto simpleDto = SimpleDto.builder()
                .writer("장성호")
                .title("제목01")
                .hit(0)
                .content("내용01")
                .password("1234")
                .build();
        int result = simpleService.write(simpleDto);
        assertThat(result).isEqualTo(1); //@Transactional 붙어 있으면 자동 롤백해줌.
    }

    @Test
    void deleteByIdTest() {
        int result = simpleService.deleteById(2);
        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("findById 테스트")
    void findById() {
        SimpleDto simpleDto = simpleService.findById(2);
        assertThat(simpleDto).isNotNull();
    }
}