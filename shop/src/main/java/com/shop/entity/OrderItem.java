package com.shop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class OrderItem extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item; //하나의 상품은 여러 주문 상품으로 들어갈 수 있으므로 주문 상품 기준 다대일 매핑.

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order; //한번의 주문에 여러 개의 상품을 주문할 수 있으므로, 주문 상품 기준 다대일 매핑.

    private int orderPrice; // 주문 가격

    private int count; // 수량

    public static OrderItem createOrderItem(Item item, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item); //주문 상품 세팅
        orderItem.setCount(count); //주문 수량 세팅
        orderItem.setOrderPrice(item.getPrice());

        item.removeStock(count); //주문 사량만큼 재고 감소.
        return orderItem;
    }

    public int getTotalPrice() {
        return orderPrice * count; //가격 계산 메소드 (주문가격 * 수량)
    }


}
