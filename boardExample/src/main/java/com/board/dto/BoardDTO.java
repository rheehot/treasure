package com.board.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class BoardDTO {

    private Long bno;
    private String title;
    private String content;
    private String writer;
    private Date regDate;
    private Date updateDate;
}
