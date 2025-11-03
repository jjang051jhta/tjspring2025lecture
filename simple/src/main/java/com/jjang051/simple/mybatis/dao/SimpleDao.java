package com.jjang051.simple.mybatis.dao;

import com.jjang051.simple.mybatis.dto.SimpleDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SimpleDao {
    List<SimpleDto> findAll();
    int write(SimpleDto simpleDto);
    int deleteById(int id);
    SimpleDto findById(int id);
}
