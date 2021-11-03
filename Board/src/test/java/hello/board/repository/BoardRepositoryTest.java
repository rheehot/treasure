package hello.board.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import hello.board.entity.Board;
import hello.board.entity.Member;
import hello.board.entity.QBoard;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void testInsert() {
        IntStream.rangeClosed(1, 100).forEach(i -> {

            Member member = Member.builder().email("userName" + i + "@gmail.com").build();

            Board board = Board.builder()
                    .title("title" + i)
                    .content("content" + i)
                    .writer(member)
                    .build();

            boardRepository.save(board);
        });
    }


    @Test
    public void testUpdate() {
        Optional<Board> result = boardRepository.findById(300L);

        if (result.isPresent()) {

            Board board = result.get();

            board.changeTitle("change Title");
            board.changeContent("change Content");

            boardRepository.save(board);
        }
    }

//    //단일 항목 검색
//    @Test
//    public void testQuery1() {
//
//        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
//
//        // 1. q도메인 클래스 얻어오기. q도메인 클래스를 이용하면, 엔티티 클래스에 선언된 title, content과 괕은 필드들을 변수로 활용 가능
//        QBoard qBoard = QBoard.board;
//
//        String keyword = "1";
//
//        // 2. BooleanBuilder는 where문에 들어가는 조건들을 넣어주는 컨테이너
//        BooleanBuilder builder = new BooleanBuilder();
//
//        // 3. 원하는 조건은 필드 값과 같이 결합해서 생성
//        BooleanExpression expression = qBoard.title.contains(keyword);
//        // 4. 만들어진 조건 where문에 and나 or같은 키워드와 결합
//        builder.and(expression);
//        // 5. BooleanBuilder는 BoardRepository에 추가된 QuerydslPredicateExcutor 인터페이스의 findAll()을 사용할 수 있다.
//        Page<Board> result = boardRepository.findAll(builder, pageable);
//
//        result.stream().forEach(board -> {
//            System.out.println(board);
//        });
//    }

//    //다중 항목 검색
//    @Test
//    public void testQuery2() {
//        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
//        QBoard qBoard = QBoard.board;
//        String keyword = "1";
//        BooleanBuilder builder = new BooleanBuilder();
//        BooleanExpression exTitle = qBoard.title.contains(keyword);
//        BooleanExpression exContent = qBoard.content.contains(keyword);
//        BooleanExpression exAll = exTitle.or(exContent);
//        builder.and(exAll);
//        builder.and(qBoard.bno.gt(0L));
//        Page<Board> result = boardRepository.findAll(builder, pageable);
//
//        result.stream().forEach(board -> {
//            System.out.println(board);
//        });
//    }

    @Transactional
    @Test
    public void testRead1() {
        //ManyToOne 관계이므로 left outer join 처리가 되어있다.
        Optional<Board> result = boardRepository.findById(10L);

        Board board = result.get();

        System.out.println(board);
        System.out.println(board.getWriter());
    }

    @Test
    public void testReadWithWriter() {
        Object result = boardRepository.getBoardWithWriter(309L);

        Object[] arr = (Object[]) result;

        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void testGetBoardWithReply() {
        List<Object[]> result = boardRepository.getBoardWithReply(309L);

        for (Object[] arr : result) {
            System.out.println(Arrays.toString(arr));
        }
    }

    @Test
    public void testWithReplyCount() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());

        Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageable);

        result.get().forEach(row -> {
            Object[] arr = (Object[]) row;
            System.out.println(Arrays.toString(arr));
        });
    }

    @Test
    public void testRead3() {
        Object result = boardRepository.getBoardByBno(309L);

        Object[] arr = (Object[]) result;

        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void testSearch1() {
        boardRepository.search1();
    }

    @Test
    public void testSearchPage() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending().and(Sort.by("title").ascending()));

        Page<Object[]> result = boardRepository.searchPage("t", "1", pageable);
    }
}
