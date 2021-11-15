package com.shop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardSearchDto {

    private String searchDateType; //검색 타입

    private String searchBy; //제목, 작성자

    private String searchQuery = ""; //조회할 검색어 저장할 변수. searchBy가 제목이면, title / 작성자면 createdBy

}
