package com.jjang051.pinterest.service;

import com.jjang051.pinterest.dto.ImgDto;
import com.jjang051.pinterest.repository.ImageRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ImageServiceTest {

    @Autowired
    private ImageService imageService;
    @Autowired
    private ImageRepository imageRepository;

    @Test
    @DisplayName("이미지 저장 테스트")
    @Rollback(false)
    void save() {
        ImgDto  imgDto = ImgDto.builder()
                .img("/images/05.jpg")
                .title("테스트 타이틀")
                .description("테스트 디스크립션")
                .point(4.5)
                .category("paint")
                .build();
        imageService.save(imgDto);
    }
}