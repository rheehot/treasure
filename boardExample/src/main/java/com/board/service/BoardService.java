package com.board.service;

import com.board.dto.BoardDTO;
import com.board.dto.Criteria;

import java.util.List;

public interface BoardService {

    //    List<BoardDTO> boardList() throws Exception;
    List<BoardDTO> boardList(Criteria cri) throws Exception;

    int totalBoardList() throws Exception;

    BoardDTO detail(Long bno) throws Exception;

    boolean update(BoardDTO boardDTO) throws Exception;

    boolean delete(Long bno) throws Exception;

    void insert(BoardDTO boardDTO) throws Exception;
}
