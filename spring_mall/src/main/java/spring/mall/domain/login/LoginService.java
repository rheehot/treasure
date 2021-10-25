package spring.mall.domain.login;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.mall.domain.member.Member;
import spring.mall.domain.member.MemberRepository;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    // -> null이면 로그인 실패
    public Member login(String loginId, String password) {

        return memberRepository.findByLoginId(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }
}
