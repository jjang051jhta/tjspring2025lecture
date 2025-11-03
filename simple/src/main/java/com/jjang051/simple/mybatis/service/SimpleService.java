package com.jjang051.simple.mybatis.service;

import com.jjang051.simple.mybatis.dao.SimpleDao;
import com.jjang051.simple.mybatis.dto.SimpleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SimpleService {
    private final SimpleDao simpleDao;

    public List<SimpleDto> findAll(){
        return simpleDao.findAll();
    }
    public int write(SimpleDto simpleDto){
        return simpleDao.write(simpleDto);
    }
    public int deleteById(int id){
        return simpleDao.deleteById(id);
    }

    public SimpleDto findById(int id) {
        return simpleDao.findById(id);
    }
}
