package com.shop.entity;

import com.shop.dto.BoardFormDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "board")
@NoArgsConstructor
public class Board extends BaseEntity {
    @Id
    @Column(name = "board_id")
    @GeneratedValue
    private Long id;
    private String title;
    private String content;


    @Builder
    public Board(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
