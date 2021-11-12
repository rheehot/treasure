package com.shop.dto;

import com.shop.entity.Board;
import lombok.*;
import org.modelmapper.ModelMapper;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BoardFormDto {
    private Long id;
    @Column(nullable = false)
    @NotBlank(message = "제목은 필수 입력 값입니다.")
    private String title;
    @Column(nullable = false)
    @NotBlank(message = "내용은 필수 입력 값입니다.")
    private String content;

    public Board toEntity() {
        Board build = Board.builder()
                .id(id)
                .title(title)
                .content(content)
                .build();
        return build;
    }

    @Builder
    public BoardFormDto(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;

    }
}

