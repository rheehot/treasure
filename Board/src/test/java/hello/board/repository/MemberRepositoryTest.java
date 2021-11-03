package hello.board.repository;

import hello.board.entity.Member;
import hello.board.entity.MemberRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.stream.IntStream;


@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertMembers() {

        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = Member.builder()
                    .email("user" + i + "@gmail.com")
                    .password("1234")
                    .name("user" + i)
                    .build();

            memberRepository.save(member);
        });
    }

    @Test
    public void insertMembersSecurity() {

        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = Member.builder()
                    .email("user" + i + "@gmail.com")
                    .name("userName" + i)
                    .fromSocial(false)
                    .password(passwordEncoder.encode("1234"))
                    .build();

            //default role
            member.addMemberRole(MemberRole.USER);

            if (i > 80) {
                member.addMemberRole(MemberRole.USER);
            }
            if (i > 90) {
                member.addMemberRole(MemberRole.ADMIN);
            }
            memberRepository.save(member);
        });
    }

    @Test
    public void testRead() {
        Optional<Member> result = memberRepository.findByEmail("user90@gmail.com",false);

        Member member = result.get();

        System.out.println(member);
    }
}