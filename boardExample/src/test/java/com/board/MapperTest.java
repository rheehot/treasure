package com.board;

import com.board.dao.BoardDAO;
import com.board.dto.BoardDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MapperTest {

    @Autowired
    private BoardDAO boardDAO;

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

}
