package hello.board.repository;

import hello.board.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {

    //@EntityGraph를 이용해서 left outer join으로 MemberRole 처리
    @EntityGraph(attributePaths = {"roleSet"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT m FROM Member m WHERE m.fromSocial = :social and m.email =:email")
    Optional<Member> findByEmail(@Param("email") String email, @Param("social") boolean social);


}
