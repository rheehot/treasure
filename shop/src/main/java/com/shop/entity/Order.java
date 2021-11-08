package com.shop.entity;

import com.shop.constant.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member; // 한명의 회원은 여러 번 주문을 할 수 있기 때문에 다대일 매핑.

    private LocalDateTime orderDate; //주문일
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus; //주문상태
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true) // 부모 엔티티의 영속성 상태 변화를 자식엔티티에 모두 전이
    // 주문상품 엔티티와 일대 다 매핑. 연관관계 주인 설정. mappedBy = order는 OrderItem 클래스에 있는 Order에 의해 관리된다는 의미.
    private List<OrderItem> orderItems = new ArrayList<>(); // 하나의 주문이 여러 개의 주문 상품을 갖으므로 List 사용.

    private LocalDateTime regTime;

    private LocalDateTime updateTime;


}
