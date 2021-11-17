package hello.board.web.dto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelloResponseDtoTest {


    @Test
    public void 롬복_기능_테스트() {
        String name = "test";
        int amount = 100;

        HelloResponseDto helloResponseDto = new HelloResponseDto(name, amount);

        Assertions.assertThat(helloResponseDto.getName()).isEqualTo(name);
        Assertions.assertThat(helloResponseDto.getAmount()).isEqualTo(amount);

    }

}