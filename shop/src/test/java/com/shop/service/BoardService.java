package com.shop.service;

import com.shop.entity.Board;
import com.shop.repository.BoardRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BoardService {

    @Autowired //스프링 컨테이너에 등록된 객체(Bean)을 주입 받는다.
    BoardRepository boardRepository;

    @Test
    void save() {
        // 1. 게시글 파라미터 생성
        Board params = Board.builder()
                .title("1번 게시글 제목")
                .content("1번 게시글 내용")
                .deleteYn('N')
                .build();

        // 2. 게시글 저장
        boardRepository.save(params);

        //3. 1번 게시글 정보 조회
        Board entity = boardRepository.findById((long) 329).get();
        assertThat(entity.getTitle()).isEqualTo("1번 게시글 제목");
        assertThat(entity.getContent()).isEqualTo("1번 게시글 내용");
    }

    @Test
    void findAll() {
        // 1. 전체 게시글 수 조회
        long boardsCount = boardRepository.count();

        //2. 전체 게시글 리스트 조회
        List<Board> list = boardRepository.findAll();
    }

    @Test
    void delete() {
        //1. 게시글 조회
        Board entity = boardRepository.findById((long) 330).get();

        //2. 게시글 삭제
        boardRepository.delete(entity);
    }
}
