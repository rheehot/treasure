package com.board.controller;

import com.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardController {

    @Autowired
    BoardService boardService;

    @RequestMapping("/list")
    private String boardList(Model model) throws Exception {
        model.addAttribute("list", boardService.boardList());

        return "list";
    }

    @RequestMapping("/detail/{bno}")
    public String detail(@PathVariable Long bno, Model model) throws Exception {
        model.addAttribute("detail", boardService.detail(bno));

        return "detail";
    }

}
