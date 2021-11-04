package hello.board.controller;

import hello.board.entity.Member;
import hello.board.security.dto.AuthMemberDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/sample")
public class MemberController {
    @GetMapping("/member")
    public String exMember(@AuthenticationPrincipal AuthMemberDTO authMemberDTO) {
        log.info("exMember = {}", authMemberDTO);

        return "sample/member";
    }

}
