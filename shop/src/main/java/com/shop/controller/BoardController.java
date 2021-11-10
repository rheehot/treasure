package com.shop.controller;

import com.shop.dto.BoardDto;
import com.shop.entity.Board;
import com.shop.repository.BoardRepository;
import com.shop.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardRepository boardRepository;
    private final BoardService boardService;

    @GetMapping(value = "/new")
    public String BoardForm(Model model) {
        model.addAttribute("BoardDto", new BoardDto());
        return "board/boardForm";
    }

    @PostMapping(value = "/new")
    public String newBoard(@Valid BoardDto boardDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "board/boardForm";
        }
        try {
            Board board = Board.createBoard(boardDto);
            boardService.save(board);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "board/boardForm";
        }
        return "redirect:/";
    }

    @GetMapping("/list")
    public String Board(Model model) {
        List<Board> list = boardRepository.findAll();
        model.addAttribute("list", list);
        return "board/list";
    }


}