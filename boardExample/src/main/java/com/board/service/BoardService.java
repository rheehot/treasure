package com.board.service;

import com.board.dto.BoardDTO;

import java.util.List;

public interface BoardService {

    List<BoardDTO> boardList() throws Exception;
    
    BoardDTO detail(Long bno) throws Exception;
}
