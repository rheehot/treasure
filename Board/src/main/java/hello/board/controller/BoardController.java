package hello.board.controller;

import hello.board.dto.BoardDTO;
import hello.board.dto.PageRequestDTO;
import hello.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/board")
@Slf4j
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/")
    public String index() {

        return "redirect:/board/list";
    }

    @GetMapping("/list")
    public String list(PageRequestDTO pageRequestDTO, Model model) {
        log.info("list = {}", pageRequestDTO);
        model.addAttribute("result", boardService.getList(pageRequestDTO));

        return "board/list";
    }

    @GetMapping("/register")
    public String registerForm() {
        log.info("register");

        return "board/register";
    }

    @PostMapping("/register")
    public String register(BoardDTO dto, RedirectAttributes redirectAttributes) {

        Long bno = boardService.register(dto);

        redirectAttributes.addAttribute("msg", bno);

        return "redirect:/board/list";
    }

    @GetMapping("/read")
    public String read(Long bno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {

        log.info("bno = {}", bno);

        BoardDTO dto = boardService.get(bno);

        model.addAttribute("dto", dto);

        return "board/read";
    }

    @GetMapping("/modify")
    public String modify(Long bno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {
        log.info("bno = {}", bno);

        BoardDTO dto = boardService.get(bno);

        model.addAttribute("dto", dto);

        return "board/modify";
    }

    @PostMapping("/modify")
    public String modify(BoardDTO dto, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, RedirectAttributes redirectAttributes) {
        // BoardDTO = 수정해야 하는 글의 정보를 가지는,
        // PageRequestDTO = 기존의 페이지 정보를 유지하기 위한,
        // RedirectAttributes = 리다이렉트로 이동하기 위한,

        log.info("modify dto = {}", dto);

        boardService.modify(dto);

        redirectAttributes.addAttribute("page", requestDTO.getPage());
        redirectAttributes.addAttribute("type", requestDTO.getType());
        redirectAttributes.addAttribute("keyword", requestDTO.getKeyword());
        redirectAttributes.addAttribute("bno", dto.getBno());

        return "redirect:/board/read";
    }

    @PostMapping("/delete")
    public String delete(Long bno, RedirectAttributes redirectAttributes) {

        log.info("bno = {}", bno);

        boardService.removeWithReplies(bno);

        redirectAttributes.addAttribute("msg", bno);

        return "redirect:/board/list";
    }





}
