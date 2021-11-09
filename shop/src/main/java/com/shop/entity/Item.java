package com.shop.entity;

import com.shop.constant.ItemSellStatus;
import com.shop.dto.ItemFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "item")
@Getter
@Setter
@ToString
public class Item extends BaseEntity{
    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.AUTO) // JPA 구현체가 자동으로 생선 전략 결정
    private Long id; //상품 코드
    @Column(nullable = false, length = 100) // not null 제약조건 추가
    private String itemName; //상품명
    @Column(name = "price", nullable = false)
    private int price; //가격
    @Column(nullable = false)
    private int stockNumber; //재고수량
    @Lob
    @Column(nullable = false)
    private String itemDetail; //상품상세설명
    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus; //상품 판매 상태
//    private LocalDateTime regTime; //등록 시간
//    private LocalDateTime updateTime; //수정 시간

    public void updateItem(ItemFormDto itemFormDto) {
        this.itemName = itemFormDto.getItemName();
        this.price = itemFormDto.getPrice();
        this.stockNumber = itemFormDto.getStockNumber();
        this.itemDetail = itemFormDto.getItemDetail();
        this.itemSellStatus = itemFormDto.getItemSellStatus();
    }

}
