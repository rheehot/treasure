package spring.mall.domain.member;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class Member {
    //회원가입 클래스

    private Long id;
    @NotEmpty
    private String loginId; //로그인 아이디
    @NotEmpty
    private String name; //사용자 이름
    @NotEmpty
    private String password; //사용자 패스워드
}



