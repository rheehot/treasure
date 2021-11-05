package hello.board.security.service;

import hello.board.entity.Member;
import hello.board.repository.MemberRepository;
import hello.board.security.dto.AuthMemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("UserDetailService loadUserByUsername = {}", username);

        Optional<Member> result = memberRepository.findByEmail(username, false); // username이 실제론 email을 의미.

        if (result.isEmpty()) {
            throw new UsernameNotFoundException("메일 또는 소셜을 확인해주세요."); // 사용자가 존재하지 않은 경우.
        }

        Member member = result.get();

        log.info("member = {}", member);

        // Member를 UserDetail 타입으로 처리하기 위해 AuthMemberDTO 타입으로 변환
        AuthMemberDTO authMemberDTO = new AuthMemberDTO(
                member.getEmail(),
                member.getPassword(),
                member.isFromSocial(),
                member.getRoleSet().stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name())).collect(Collectors.toSet())
        ); // MemberRole은 스프링 시큐리티에 있는 SimpleGrantedAuthority로 변환.

        authMemberDTO.setName(member.getUsername());
        authMemberDTO.setFromSocial(member.isFromSocial());

        return authMemberDTO;
    }
}
