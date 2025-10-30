package com.jjang051.jpa.dto;

import com.jjang051.jpa.entity.Board;
import com.jjang051.jpa.entity.Member;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDto {
    private Integer id;
    private String content;
    private Member writer;
    private Board board;

}
