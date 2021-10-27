package spring.mall.domain.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Slf4j
@Repository
public class MemberRepository {

    @PersistenceContext
    EntityManager em;

    private static Map<Long, Member> store = new HashMap<>(); //static

    public Long save(Member member) {
        em.persist(member);
//        log.info("save : member={}", member);
//        store.put(member.getId(), member); // -> getId로 찾고, member를 집어넣어준다.
        return member.getId();
    }

    public Member findById(Long id) {
        return em.find(Member.class, id);
//        return store.get(id); // -> key가 나오면 value가 나온다.
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
