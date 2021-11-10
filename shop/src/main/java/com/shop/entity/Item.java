package com.shop.entity;

import com.shop.constant.ItemSellStatus;
import com.shop.dto.ItemFormDto;
import com.shop.exception.OutOfStockException;
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
public class Item extends BaseEntity {
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

    //재고 감소 로직
    public void removeStock(int stockNumber) {
        //상품 재고수량에서 주문 후 남은 재고 수량 구하기.
        int restStock = this.stockNumber - stockNumber;
        if (restStock < 0) {
            throw new OutOfStockException("상품의 재고가 부족합니다. (현재 재고 : " + this.stockNumber + ")");
        }
        // 주문 후 남은 재고 수량을 현재 재고 값으로 할당.
        this.stockNumber = restStock;
    }

    public void addStock(int stockNumber){
        this.stockNumber += stockNumber;
    }


}
