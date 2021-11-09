package com.shop.service;

import com.shop.dto.BoardDto;
import com.shop.entity.Board;
import com.shop.entity.Member;
import com.shop.repository.BoardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BoardServiceTest {

    @Autowired
    BoardRepository boardRepository;

    public Board createBoard() {
        BoardDto boardDto = new BoardDto();
        Member member = new Member();
        boardDto.setTitle("테스트 제목");
        boardDto.setContent("테스트 내용");
//        boardDto.setWriter(member.getEmail());
        return Board.createBoard(boardDto);
    }

    @Test
    @DisplayName("글 등록 테스트")
    public void saveBoardTest() {
        Board board = createBoard();

        Board savedBoard = boardRepository.save(board);

        assertEquals(board.getTitle(), savedBoard.getTitle());
        assertEquals(board.getContent(), savedBoard.getContent());
//        assertEquals(board.getWriter(), savedBoard.getWriter());
    }


}