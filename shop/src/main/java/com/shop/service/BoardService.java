package com.shop.service;

import com.shop.entity.Board;
import com.shop.repository.BoardRepository;
import com.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    
    public Board save(Board board) {
        return boardRepository.save(board);
    }
}
