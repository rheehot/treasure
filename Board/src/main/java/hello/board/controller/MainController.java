package hello.board.controller;

import hello.board.config.SecurityConfig;
import hello.board.security.service.OAuth2UserDetailsService;
import hello.board.security.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class MainController {


    @RequestMapping("/")
    public String index() {
        return "redirect:/login";
    }
    
}
