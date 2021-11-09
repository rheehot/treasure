package com.shop.service;

import com.shop.dto.BoardDto;
import com.shop.entity.BaseEntity;
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
//        board.setWriter(board.getCreatedBy());
        return boardRepository.save(board);
    }


}
