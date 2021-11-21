package com.memo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "memo")
@Getter
@Setter
@ToString
public class Memo {
    @Id
    @Column(name = "memo_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; //메모 코드
    @Column(nullable = false)
    private String title; //메모 제목
    @Column(nullable = false, length = 5000)
    private String content; //메모 내용
    private LocalDateTime regTime; //등록 시간
    private LocalDateTime updateTime; //수정 시간

}
