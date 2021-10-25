package spring.mall.domain.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>(); //static
    private static long sequence = 0L; //static

    public Member save(Member member) {
        member.setId(++sequence);
        log.info("save : member={}", member);
        store.put(member.getId(), member); // -> getId로 찾고, member를 집어넣어준다.
        return member;
    }

    public Member findById(Long id) {
        return store.get(id); // -> key가 나오면 value가 나온다.
    }

    public Optional<Member> findByLoginId(String loginId) {
        // -> m.getLoginId().equals(loginId) m.getLoginId가 파라미터로 넘어온 loginId와 같은가를 비교.
        return findAll().stream()
                .filter(m -> m.getLoginId().equals(loginId))
                .findFirst();
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // -> store에 value만 뽑아서 반환
    }

    public void clearStore() {
        store.clear();
    }


}
