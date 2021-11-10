package com.shop.entity;

import com.shop.constant.OrderStatus;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; // 한명의 회원은 여러 번 주문을 할 수 있기 때문에 다대일 매핑.

    private LocalDateTime orderDate; //주문일
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus; //주문상태
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    // 부모 엔티티의 영속성 상태 변화를 자식엔티티에 모두 전이
    // 주문상품 엔티티와 일대 다 매핑. 연관관계 주인 설정. mappedBy = order는 OrderItem 클래스에 있는 Order에 의해 관리된다는 의미.
    private List<OrderItem> orderItems = new ArrayList<>(); // 하나의 주문이 여러 개의 주문 상품을 갖으므로 List 사용.


    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem); //orderItems에 주문 상품 정보들 담기.
        orderItem.setOrder(this); //Order 엔티티와 OrderItem 엔티티는 양방향 참조. 그러므로 OrderItem에도 order 객체 세팅.
    }

    public static Order createOrder(Member member, List<OrderItem> orderItemList) {
        Order order = new Order();
        order.setMember(member); //상품을 주문한 회원 정보 세팅.
        for (OrderItem orderItem : orderItemList) {
            order.addOrderItem(orderItem);
        }
        order.setOrderStatus(OrderStatus.ORDER); //주문 상태를 order로 세팅.
        order.setOrderDate(LocalDateTime.now()); //현재 시간을 주문시간으로 세팅.
        return order;
    }

    // 총 주문금액 구하는 메소드.
    public int getTotalPrice() {
        int totalPrice = 0;
        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }

    public void cancelOrder() {
        this.orderStatus = OrderStatus.CANCEL;
        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }


}
