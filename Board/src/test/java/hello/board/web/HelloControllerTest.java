package hello.board.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    @Autowired // 스프링이 관리하는 빈
    private MockMvc mvc; //이 클래스를 통해서 HTTP GET, POST등에 대한 API 테스트를 할 수 있다.

    @Test
    public void hello_test() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello")) // /hello주소로 HTTP GET 요청.
                .andExpect(status().isOk()) // HTTP header의 Status 검증(200,400,500).
                .andExpect(content().string(hello)); // mvc.perform의 결과를 검증. 컨트롤러에서 "hello"를 리턴하기 때문에 이 값이 맞는지 검증.
    }
}
