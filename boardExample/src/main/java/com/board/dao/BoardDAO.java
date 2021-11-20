package com.board.dao;

import com.board.dto.BoardDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("com.board.dao.BoardDAO")
public interface BoardDAO {
    //목록
    List<BoardDTO> boardList() throws Exception;

    //상세
    BoardDTO detail(Long bno) throws Exception;

    //등록
    void insert(BoardDTO boardDTO) throws Exception;

    //삭제
    int delete(Long bno) throws Exception;

    //수정
    int update(BoardDTO boardDTO) throws Exception;


}
