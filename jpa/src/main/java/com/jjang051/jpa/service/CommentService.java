package com.jjang051.jpa.service;

import com.jjang051.jpa.dto.CommentDto;
import com.jjang051.jpa.entity.Comment;
import com.jjang051.jpa.repository.CommentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public Comment save(CommentDto commentDto) {
        Comment comment = Comment.builder()
                .content(commentDto.getContent())
                .writer(commentDto.getWriter())
                .board(commentDto.getBoard())
                .build();
        return commentRepository.save(comment);
    }

    public Boolean deleteBoard(int id) {

        commentRepository.deleteById(id);
        return commentRepository.existsById(id);
    }
}
