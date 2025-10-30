package com.jjang051.jpa.service;

import com.jjang051.jpa.dto.BoardDto;
import com.jjang051.jpa.entity.Board;
import com.jjang051.jpa.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {
    private final BoardRepository boardRepository;

    public Board writeBoard(BoardDto boardDto){
        System.out.println("boardDto==="+boardDto);
        Board board = Board.builder()
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .writer(boardDto.getWriter())
                .hit(0)
                .build();
        return boardRepository.save(board);
    }

    public List<Board> getList() {
        return boardRepository.findAll();
    }

    public Board getBoard(int id) {
        return boardRepository.findById(id).orElse(null);
    }

    public void deleteBoard(int id) {
        boardRepository.deleteById(id);
    }
}
