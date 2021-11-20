package com.board;

import com.board.dao.BoardDAO;
import com.board.dto.BoardDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class MapperTest {

    @Autowired
    private BoardDAO boardDAO;

    //리스트
    @Test
    public void testList() throws Exception {
        List<BoardDTO> list = boardDAO.boardList();

        for (BoardDTO boardDTO : list) {
            System.out.println("bno : " + boardDTO.getBno());
            System.out.println("title : " + boardDTO.getTitle());
            System.out.println("regDate : " + boardDTO.getRegDate());
            System.out.println("updateDate : " + boardDTO.getUpdateDate());
        }
    }

    //등록
    @Test
    public void testInsert() throws Exception {
        BoardDTO board = new BoardDTO();
        board.setTitle("test");
        board.setContent("test");
        board.setWriter("test");
        boardDAO.insert(board);
    }

    //상세
    @Test
    public void testDetail() throws Exception {
        BoardDTO board = boardDAO.detail(1L);

        log.info("board : {}", board);
    }

    //삭제
    @Test
    public void testDelete() throws Exception {
        log.info("DELETE COUNT : {}", boardDAO.delete(3L));
    }

    //수정
    @Test
    public void testUpdate() throws Exception {
        BoardDTO board = new BoardDTO();
        board.setBno(4L);
        board.setTitle("수정 제목");
        board.setContent("수정 내용");
        board.setWriter("user123");
        int count = boardDAO.update(board);
        log.info("UPDATE INFO = {}", count);

    }


}
