package com.shop.service;


import com.shop.dto.BoardFormDto;
import com.shop.entity.Board;
import com.shop.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Long savePost(BoardFormDto boardFormDto) {
        return boardRepository.save(boardFormDto.toEntity()).getId();
    }

    public List<Board> getList() {
        return boardRepository.findAll();
    }

    @Transactional
    public BoardFormDto getPost(Long id) {
        Optional<Board> boardWrapper = boardRepository.findById(id);
        Board board = boardWrapper.get();

        BoardFormDto boardFormDto = BoardFormDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .build();

        return boardFormDto;
    }

    @Transactional
    public void deletePost(Long id) {
        boardRepository.deleteById(id);
    }

}
