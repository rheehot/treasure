package com.shop.repository;

import com.shop.dto.BoardFormDto;
import com.shop.dto.BoardSearchDto;
import com.shop.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardRepositoryCustom {

    Page<Board> getBoardPage(BoardSearchDto boardSearchDto, Pageable pageable); //조회 조건을 담고 있는 boardSearchDto, 페이징 정보를 담고있는 pageable
}
