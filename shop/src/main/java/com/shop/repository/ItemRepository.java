package com.shop.repository;

import com.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    //JpaRepository<Item, Long> 엔티티 타입 클래스, 기본키 타입

    //상품명 찾기
    List<Item> findByItemName(String itemName);

    //상품명 또는 내용으로 찾기
    List<Item> findByItemNameOrItemDetail(String itemName, String itemDetail);

    //파라미터로 넘어온 price 변수보다 값이 작은 상품 데이터를 조회하는 쿼리
    List<Item> findByPriceLessThan(Integer price);

    //ORDER BY로 정렬 처리
    List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);

}
