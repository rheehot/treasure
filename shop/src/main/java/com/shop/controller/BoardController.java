package com.shop.controller;


import com.shop.dto.BoardFormDto;
import com.shop.entity.Board;
import com.shop.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping(value = "/list")
    public String list(Model model) {
        List<Board> list = boardService.getList();
        model.addAttribute("list", list);
        return "board/list";
    }

    @GetMapping(value = "/post")
    public String write() {
        return "board/write";
    }

    @PostMapping(value = "/post")
    public String write(BoardFormDto boardFormDto) {
        boardService.savePost(boardFormDto);
        return "redirect:/";
    }

    @GetMapping("/post/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        BoardFormDto boardFormDto = boardService.getPost(id);
        model.addAttribute("boardFormDto", boardFormDto);
        return "board/detail";
    }

    @GetMapping("/post/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        BoardFormDto boardFormDto = boardService.getPost(id);

        model.addAttribute("boardFormDto", boardFormDto);

        return "board/update";
    }

    @PutMapping("/post/edit/{id}")
    public String update(BoardFormDto boardFormDto) {
        boardService.savePost(boardFormDto);
        return "redirect:/";
    }

    @DeleteMapping("/post/{id}")
    public String delete(@PathVariable("id") Long id) {
        boardService.deletePost(id);

        return "redirect:/";
    }


}
