package hello.board.repository;

import hello.board.entity.Board;
import hello.board.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    //Board 삭제시에 댓글들 삭제
    @Modifying
    @Query("delete from Reply r where r.board.bno =:bno ")
    void deleteByBno(@Param("bno") Long bno);

    //게시물로 댓글 목록 가져오기
    List<Reply> getRepliesByBoardOrderByRno(Board board);
}
