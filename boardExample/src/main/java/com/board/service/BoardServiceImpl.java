package com.board.service;

import com.board.dao.BoardDAO;
import com.board.dto.BoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardDAO boardDAO;

    @Override
    public List<BoardDTO> boardList() throws Exception {
        return boardDAO.boardList();
    }

    @Override
    public BoardDTO detail(Long bno) throws Exception {
        return boardDAO.detail(bno);
    }

    @Override
    public boolean update(BoardDTO boardDTO) throws Exception {
        return boardDAO.update(boardDTO) == 1;
    }

    @Override
    public boolean delete(Long bno) throws Exception {
        return boardDAO.delete(bno) == 1;
    }

    @Override
    public void insert(BoardDTO boardDTO) throws Exception {
        boardDAO.insert(boardDTO);
    }
}
