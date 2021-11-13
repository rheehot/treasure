package com.shop.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.dto.BoardFormDto;
import com.shop.entity.Board;
import com.shop.entity.QBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;

public class BoardRepositoryCustomImpl implements BoardRepositoryCustom {
    //동적 쿼리 생성
    private JPAQueryFactory queryFactory;

    public BoardRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

//    @Override
//    public Page<Board> getBoardPage(BoardFormDto boardFormDto, Pageable pageable) {
//
//        QueryResults<Board> results = queryFactory.selectFrom(QBoard.board);
//    }
}
