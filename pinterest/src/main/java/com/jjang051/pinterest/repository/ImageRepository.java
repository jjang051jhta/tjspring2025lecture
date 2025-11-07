package com.jjang051.pinterest.repository;

import com.jjang051.pinterest.dto.ImgDto;
import com.jjang051.pinterest.entity.ImgEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImgEntity, Integer> {
}
