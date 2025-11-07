package com.jjang051.pinterest.service;

import com.jjang051.pinterest.dto.ImgDto;
import com.jjang051.pinterest.entity.ImgEntity;
import com.jjang051.pinterest.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;
    public List<ImgDto> findAll(){
        /*
        List<ImgDto> list = new ArrayList<>();
        ImgDto imgDto01 = ImgDto.builder()
                .img("/images/01.jpg")
                .title("aaa to the unknown man")
                .point(4.9)
                .desc("The United States saw the most new coronavirus cases of the pandemic on Friday, with deaths and hospitalizations also rising. Underlying conditions largely determine who survives.")
                .category("paint")
                .build();
        ImgDto imgDto02 = ImgDto.builder()
                .img("/images/02.jpg")
                .title("bbb to the unknown man")
                .point(4.75)
                .desc("The United States saw the most new coronavirus cases of the pandemic on Friday, with deaths and hospitalizations also rising. Underlying conditions largely determine who survives.")
                .category("photo")
                .build();
        list.add(imgDto01);
        list.add(imgDto02);
         */
        List<ImgDto> list = imageRepository.findAll().stream()
                            .map(e->ImgDto.from(e))
                .toList();
        List<ImgDto> list02 = imageRepository.findAll().stream()
                .map(ImgDto::from)
                .toList();

        System.out.println("size==="+list.size());
        return list02;
    }
    public void save(ImgDto imgDto){
        imageRepository.save(imgDto.toEntity());
    }
}
