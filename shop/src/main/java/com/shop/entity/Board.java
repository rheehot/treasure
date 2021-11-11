package com.shop.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Board extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private char deleteYn;

    @Builder //생성자를 대신하는 놈.
    public Board(String title, String content, char deleteYn) {
        this.title = title;
        this.content = content;
        this.deleteYn = deleteYn;
    }
}
