package hello.board.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/introduce")
public class introduceController {

    @GetMapping("/list")
    public String list() {
        return "introduce/list";
    }
}
