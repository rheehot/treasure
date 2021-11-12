package com.shop.controller;


import com.shop.dto.BoardFormDto;
import com.shop.entity.Board;
import com.shop.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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
    public String write(@Valid BoardFormDto boardFormDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "board/write";
        }
        try {
            boardService.savePost(boardFormDto);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "board/write";
        }
        return "redirect:/board/list";
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
    public String update(@Valid BoardFormDto boardFormDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "board/list";
        }
        boardService.savePost(boardFormDto);
        return "redirect:/board/list";
    }

    @DeleteMapping("/post/{id}")
    public String delete(@PathVariable("id") Long id) {
        boardService.deletePost(id);

        return "redirect:/board/list";
    }


}
