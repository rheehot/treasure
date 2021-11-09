package com.shop.entity;

import com.shop.dto.BoardDto;
import com.shop.dto.MemberFormDto;
import com.shop.repository.MemberRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
//@Table(name = "board")
public class Board extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String content;

    private String writer;


    public static Board createBoard(BoardDto boardDto) {
        Board board = new Board();
        board.setTitle(boardDto.getTitle());
        board.setContent(boardDto.getContent());
        board.setWriter(boardDto.getWriter());
        return board;
    }
}
