package spring.mall;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import spring.mall.domain.member.Member;
import spring.mall.domain.member.MemberRepository;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final MemberRepository memberRepository;

    @PostConstruct
    public void init() {
        Member member = new Member();
        member.setLoginId("admin");
        member.setPassword("1234");
        member.setName("test");

        memberRepository.save(member);
    }
}
