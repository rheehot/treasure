package com.shop.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class BoardDto {

    private Long id;
    @NotBlank(message = "제목은 필수 입력 값입니다.")
    private String title;
    @NotBlank(message = "내용은 필수 입력 값입니다.")
    private String content;
//    @NotBlank(message = "작성자는 필수 입력 값입니다.")
//    private String writer;


}