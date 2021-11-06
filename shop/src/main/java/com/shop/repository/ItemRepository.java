package com.shop.repository;

import com.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long>, QuerydslPredicateExecutor<Item> {
    //JpaRepository<Item, Long> 엔티티 타입 클래스, 기본키 타입
    //상품명 찾기
    List<Item> findByItemName(String itemName);

    //상품명 또는 내용으로 찾기
    List<Item> findByItemNameOrItemDetail(String itemName, String itemDetail);

    //파라미터로 넘어온 price 변수보다 값이 작은 상품 데이터를 조회하는 쿼리
    List<Item> findByPriceLessThan(Integer price);

    //ORDER BY로 정렬 처리
    List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);

    //상품 데이터 조회 JPQL
    @Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc")
    List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);

}
