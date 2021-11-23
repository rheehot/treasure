package com.board.controller;

import com.board.dto.BoardDTO;
import com.board.dto.Criteria;
import com.board.dto.PageMaker;
import com.board.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
public class BoardController {

    @Autowired
    BoardService boardService;

    //    @GetMapping("/list")
//    private String boardList(Model model) throws Exception {
//        model.addAttribute("list", boardService.boardList());
//
//        return "list";
//    }
    @GetMapping("/list")
    private String boardList(Criteria cri, Model model) throws Exception {

        PageMaker pageMaker = new PageMaker();
        pageMaker.setCri(cri);
        pageMaker.setTotalCount(boardService.totalBoardList());
        model.addAttribute("list", boardService.boardList(cri));
        model.addAttribute("pageMaker", pageMaker);

        return "list";
    }


    @GetMapping("/detail/{bno}")
    public String detail(@PathVariable Long bno, Criteria cri, Model model) throws Exception {

        PageMaker pageMaker = new PageMaker();
        pageMaker.setCri(cri);

        model.addAttribute("page", cri.getPage());
        model.addAttribute("pageMaker", pageMaker);
        model.addAttribute("detail", boardService.detail(bno));

        return "detail";
    }

    @GetMapping("/insert")
    public String insert() {
        return "insert";
    }

    @PostMapping("/insert")
    public String insert(BoardDTO boardDTO, RedirectAttributes redirectAttributes) throws Exception {
        boardService.insert(boardDTO);
        redirectAttributes.addAttribute("result", boardDTO.getBno());
        return "redirect:/list";
    }

    @GetMapping("/update/{bno}")
    public String update(@PathVariable Long bno, Criteria cri, Model model) throws Exception {


        model.addAttribute("update", boardService.detail(bno));

        return "update";
    }

    @PostMapping("/update")
    public String update(BoardDTO boardDTO, RedirectAttributes redirectAttributes) throws Exception {
        if (boardService.update(boardDTO)) {
            redirectAttributes.addAttribute("result", "success");
        }
        return "redirect:/list";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("bno") Long bno, RedirectAttributes redirectAttributes) throws Exception {
        if (boardService.delete(bno)) {
            redirectAttributes.addAttribute("result", "success");
        }
        return "redirect:/list";
    }

}
