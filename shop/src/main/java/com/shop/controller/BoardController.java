package com.shop.controller;

import com.shop.dto.BoardDto;
import com.shop.entity.Board;
import com.shop.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping(value = "/new")
    public String BoardForm(Model model) {
        model.addAttribute("BoardDto", new BoardDto());
        return "board/boardForm";
    }

    @PostMapping(value = "/new")
    public String newBoard(@Valid BoardDto boardDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "board/boardForm";
        }
        Board board = Board.createBoard(boardDto);
        boardService.save(board);
        return "redirect:/";
    }


}