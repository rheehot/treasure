package hello.board.service;

import hello.board.dto.BoardDTO;

import hello.board.dto.PageRequestDTO;
import hello.board.dto.PageResultDTO;
import hello.board.entity.Board;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
public class BoardServiceTest {

    @Autowired
    private BoardService boardService;

    @Test
    public void testRegister() {

        BoardDTO boardDTO = BoardDTO.builder()
                .title("hkkbc 123")
                .content("hkkbc content")
                .writerEmail("hkkbc123@aaa.com")
                .build();

        System.out.println(boardService.register(boardDTO));

    }


    @Test
    public void testList() {

        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        PageResultDTO<BoardDTO, Object[]> resultDTO = boardService.getList(pageRequestDTO);

        System.out.println("PREV : " + resultDTO.isPrev());
        System.out.println("NEXT : " + resultDTO.isNext());
        System.out.println("TOTAL : " + resultDTO.getTotalPage());

        for (BoardDTO boardDTO : resultDTO.getDtoList()) {
            System.out.println(boardDTO);
        }

        resultDTO.getPageList().forEach(i -> System.out.println(i));

    }

    @Test
    public void testSearch() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .type("tc") //검색 조건
                .keyword("123") //검색 키워드
                .build();

        PageResultDTO<BoardDTO, Object[]> resultDTO = boardService.getList(pageRequestDTO);

        System.out.println("PREV : " + resultDTO.isPrev());
        System.out.println("NEXT : " + resultDTO.isNext());
        System.out.println("TOTAL : " + resultDTO.getTotalPage());

        for (BoardDTO boardDTO : resultDTO.getDtoList()) {
            System.out.println(boardDTO);
        }

        resultDTO.getPageList().forEach(i -> System.out.println(i));
    }

    @Test
    public void testGet() {
        Long bno = 412L;

        BoardDTO boardDTO = boardService.get(bno);

        System.out.println(boardDTO);
    }

    @Test
    public void testRemove() {
        Long bno = 1L;

        boardService.removeWithReplies(bno);
    }

    @Transactional
    @Test
    public void testModify() {
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(309L)
                .title("change Title")
                .content("change Content")
                .build();

        boardService.modify(boardDTO);
    }
}