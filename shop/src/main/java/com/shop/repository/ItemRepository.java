package com.shop.repository;

import com.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    //JpaRepository<Item, Long> 엔티티 타입 클래스, 기본키 타입

    //상품명 찾기
    List<Item> findByItemName(String itemName);

}
