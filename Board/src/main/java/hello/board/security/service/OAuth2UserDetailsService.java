package hello.board.security.service;

import hello.board.entity.Member;
import hello.board.entity.MemberRole;
import hello.board.repository.MemberRepository;
import hello.board.security.dto.AuthMemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuth2UserDetailsService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("userRequest = {}", userRequest);

        String clientName = userRequest.getClientRegistration().getClientName();

        log.info("clientName = {}", clientName);
        log.info("userRequest.getAdditionalParameters() = {}", userRequest.getAdditionalParameters());

        OAuth2User oAuth2User = super.loadUser(userRequest);

        oAuth2User.getAttributes().forEach((k, v) -> {
            log.info(k + " : " + v); // sub, picture, email, email_verified, email 등 로그로 나옴.
        });

        String email = null;

        if (clientName.equals("Google")) {
            //구글 이용하는 경우,
            email = oAuth2User.getAttribute("email");
        }

        log.info("EMAIL : {}", email);

//        Member member = saveSocialMember(email);
//
//        return oAuth2User;

        Member member = saveSocialMember(email);
        AuthMemberDTO memberDTO = new AuthMemberDTO(
                member.getEmail(),
                member.getPassword(),
                true,
                member.getRoleSet().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                        .collect(Collectors.toList()),
                oAuth2User.getAttributes()
        );
        memberDTO.setName(member.getUsername());

        return memberDTO;
    }


    private Member saveSocialMember(String email) {
        //기존 동일한 이메일로 가입한 회원인 경우 그대로 조회만.
        Optional<Member> result = memberRepository.findByEmail(email, true);

        if (result.isPresent()) {
            return result.get();
        }

        //없다면 회원 추가 패스워드는 1234 이름은 이메일 주소
        Member member = Member.builder().email(email)
                .username(email)
                .password(passwordEncoder.encode("1234"))
                .fromSocial(true)
                .build();

        member.addMemberRole(MemberRole.USER);

        memberRepository.save(member);

        return member;
    }
}
