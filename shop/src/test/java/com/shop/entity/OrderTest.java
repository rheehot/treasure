//package com.shop.entity;
//
//import com.shop.constant.ItemSellStatus;
//import com.shop.repository.ItemRepository;
//import com.shop.repository.MemberRepository;
//import com.shop.repository.OrderItemRepository;
//import com.shop.repository.OrderRepository;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityNotFoundException;
//import javax.persistence.PersistenceContext;
//import java.time.LocalDateTime;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@SpringBootTest
//@Transactional
//public class OrderTest {
//
//    @Autowired
//    OrderRepository orderRepository;
//
//    @Autowired
//    ItemRepository itemRepository;
//
//    @Autowired
//    MemberRepository memberRepository;
//
//    @Autowired
//    OrderItemRepository orderItemRepository;
//
//    @PersistenceContext
//    EntityManager em;
//
//    public Item createItem() {
//        Item item = new Item();
//        item.setItemName("테스트 상품");
//        item.setPrice(10000);
//        item.setItemDetail("테스트 상품 상세 설명");
//        item.setItemSellStatus(ItemSellStatus.SELL);
//        item.setStockNumber(100);
//        item.setRegTime(LocalDateTime.now());
//        item.setUpdateTime(LocalDateTime.now());
//        return item;
//    }
//
//    @Test
//    @DisplayName("영속성 전이 테스트")
//    public void cascadeTest() {
//
//        Order order = new Order();
//
//        for (int i = 0; i < 5; i++) {
//            Item item = this.createItem();
//            itemRepository.save(item);
//            OrderItem orderItem = new OrderItem();
//            orderItem.setItem(item);
//            orderItem.setCount(10);
//            orderItem.setOrderPrice(1000);
//            orderItem.setOrder(order);
//            order.getOrderItems().add(orderItem); // orderItem 엔티티를 order 엔티티에 담기.
//        }
//        orderRepository.saveAndFlush(order); // order 엔티티를 저장하며, 강제로 flush 호출해서 디비에 반영.
//        em.clear(); // 영속성 컨텍스트 초기화
//
//        Order savedOrder = orderRepository.findById(order.getId())
//                .orElseThrow(EntityNotFoundException::new); // itemOrder 엔티티 5개가 있는지 검사.
//        assertEquals(5, savedOrder.getOrderItems().size());
//    }
//
//    public Order createOrder() { //주문 데이터 생성해서 저장하는 메소드
//        Order order = new Order();
//
//        for (int i = 0; i < 5; i++) {
//            Item item = createItem();
//            itemRepository.save(item);
//            OrderItem orderItem = new OrderItem();
//            orderItem.setItem(item);
//            orderItem.setCount(10);
//            orderItem.setOrderPrice(1000);
//            orderItem.setOrder(order);
//            order.getOrderItems().add(orderItem);
//        }
//
//        Member member = new Member();
//        memberRepository.save(member);
//
//        order.setMember(member);
//        orderRepository.save(order);
//        return order;
//    }
//
//    @Test
//    @DisplayName("고아객체 제거 테스트")
//    public void orphanRemovalTest() {
//        Order order = this.createOrder();
//        order.getOrderItems().remove(1); //order 엔티티에서 관리하는 orderItem 리스트의 0번째 인덱스 요소 제거.
//        em.flush();
//    }
//
//    @Test
//    @DisplayName("지연 로딩 테스트")
//    public void lazyLoadingTest() {
//        Order order = this.createOrder(); // 주문 데이터 저장.
//        Long orderItemId = order.getOrderItems().get(0).getId();
//        em.flush();
//        em.clear();
//
//        OrderItem orderItem = orderItemRepository.findById(orderItemId)
//                .orElseThrow(EntityNotFoundException::new); // 영속성 컨텍스트 상태 초기화 후, order 엔티티에 저장했던 주문 상품 아이디를 이용해서 orderItem을 디비에서 재조회.
//
//        System.out.println("Order class = " + orderItem.getOrder().getClass()); // orderItem 엔티티에 있는 order 객체의 클래스 출력
//
//    }
//
//}
