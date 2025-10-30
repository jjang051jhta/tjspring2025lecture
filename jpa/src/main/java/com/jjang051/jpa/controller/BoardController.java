package com.jjang051.jpa.controller;

import com.jjang051.jpa.dto.BoardDto;
import com.jjang051.jpa.dto.CustomUserDetails;
import com.jjang051.jpa.entity.Board;
import com.jjang051.jpa.entity.Member;
import com.jjang051.jpa.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    @GetMapping("/list")
    public String list(Model model) {
        List<Board> boardList = boardService.getList();
        model.addAttribute("boardList", boardList);
        return "board/list";
    }

    @GetMapping("/{id}/detail")
    public String detail(@PathVariable("id") int id, Model model) {

        Board board = boardService.getBoard(id);
        model.addAttribute("board", board);
        return "board/detail";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {

        boardService.deleteBoard(id);
        redirectAttributes.addFlashAttribute("isDelete",true);
        return "redirect:/board/list";
    }


    @GetMapping("/write")
    public String write(Model model){
        model.addAttribute("boardDto", new BoardDto());
        return "board/write";
    }
    @PostMapping("/write")
    public String writeProcess(@ModelAttribute BoardDto boardDto,
                               @AuthenticationPrincipal CustomUserDetails customUserDetails){
        boardDto.setWriter(customUserDetails.getLoggedMember());
        //title,content, mem
        boardService.writeBoard(boardDto);
        return "redirect:/";
    }
}
