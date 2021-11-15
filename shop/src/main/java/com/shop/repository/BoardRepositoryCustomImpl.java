package com.shop.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.dto.BoardSearchDto;
import com.shop.entity.Board;
import com.shop.entity.QBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

public class BoardRepositoryCustomImpl implements BoardRepositoryCustom { // 상속 받기.

    private JPAQueryFactory queryFactory; //동적 쿼리 생성을 위해 JPAQueryFactory 클래스 사용.

    public BoardRepositoryCustomImpl(EntityManager em) { //EntityManager 객체 삽입.
        this.queryFactory = new JPAQueryFactory(em);
    }

    // searchDateType의 값에 따라서 dateTime의 값을 이전 시간의 값으로 세팅 후 해당 시간 이후로 등록된 상품만 조회하는 메소드
    private BooleanExpression regDtsAfter(String searchDateType) {
        LocalDateTime dateTime = LocalDateTime.now();

        if (StringUtils.equals("all", searchDateType) || searchDateType == null) {
            return null; // 타입이 all 이거나 null 이면은 리턴하지 않는다.
        } else if (StringUtils.equals("1d", searchDateType)) {
            dateTime = dateTime.minusDays(1);
        } else if (StringUtils.equals("1w", searchDateType)) {
            dateTime = dateTime.minusWeeks(1);
        } else if (StringUtils.equals("1m", searchDateType)) {
            dateTime = dateTime.minusMonths(1);
        } else if (StringUtils.equals("6m", searchDateType)) {
            dateTime = dateTime.minusMonths(6);
        }

        return QBoard.board.regTime.after(dateTime);
    }

    //searchBy에 따라서 제목을 검색 또는 작성자를 검색하는 메서드
    private BooleanExpression searchByLike(String searchBy, String searchQuery) {
        if (StringUtils.equals("title", searchBy)) {
            return QBoard.board.title.like("%" + searchBy + "%");
        } else if (StringUtils.equals("createdBy", searchBy)) {
            return QBoard.board.createdBy.like("%" + searchQuery + "%");
        }
        return null;
    }

    @Override
    public Page<Board> getBoardPage(BoardSearchDto boardSearchDto, Pageable pageable) {
        QueryResults<Board> results = queryFactory
                .selectFrom(QBoard.board) //보드 데이터를 조회하기 위해서 QBboard board를 지정.
                .where(regDtsAfter(boardSearchDto.getSearchDateType()),
                        searchByLike(boardSearchDto.getSearchBy(),
                                boardSearchDto.getSearchQuery()))
                .orderBy(QBoard.board.id.desc())
                .offset(pageable.getOffset()) //데이터를 가지고 올 시작 인덱스를 지정.
                .limit(pageable.getPageSize()) //한번에 가져올 최대 개수 지정.
                .fetchResults(); //조회한 리스트 및 전체 개수를 포함하는 쿼리리절트를 반환.
        List<Board> content = results.getResults();
        long total = results.getTotal();
        return new PageImpl<>(content, pageable, total);
    }

}
