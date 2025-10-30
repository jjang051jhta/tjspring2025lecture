package com.jjang051.jpa.controller;

import com.jjang051.jpa.dto.CommentDto;
import com.jjang051.jpa.dto.CustomUserDetails;
import com.jjang051.jpa.entity.Board;
import com.jjang051.jpa.entity.Comment;
import com.jjang051.jpa.service.BoardService;
import com.jjang051.jpa.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/comment")
@Slf4j
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final BoardService boardService;

    @PostMapping("/write/{id}")
    public String writeComment(CommentDto commentDto,
                               @AuthenticationPrincipal CustomUserDetails customUserDetails,
                               @PathVariable("id") int id
    ){
        Board findedBoard = boardService.getBoard(id);
        commentDto.setWriter(customUserDetails.getLoggedMember());
        commentDto.setBoard(findedBoard);
        commentService.save(commentDto);
        return "redirect:/board/"+id+"/detail";
    }
    @DeleteMapping("/{id}/delete")
    @ResponseBody
    public Map<String,Boolean> delete(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        boolean isDelete = commentService.deleteBoard(id);
        Map<String,Boolean> resultMap = new HashMap<>();
        resultMap.put("deleted",!isDelete);
        return resultMap;
    }
}
