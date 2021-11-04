package hello.board.security.handler;

import hello.board.security.dto.AuthMemberDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    private PasswordEncoder passwordEncoder;

    public LoginSuccessHandler(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("onAuthenticationSuccess");

        AuthMemberDTO authMemberDTO = (AuthMemberDTO) authentication.getPrincipal();

        boolean fromSocial = authMemberDTO.isFromSocial();

        log.info("modify member ?? = {}", fromSocial);

        boolean passwordResult = passwordEncoder.matches("1234", authMemberDTO.getPassword());

        if (fromSocial && passwordResult) {
            redirectStrategy.sendRedirect(request, response, "/introduce/list");
        }
    }
}
