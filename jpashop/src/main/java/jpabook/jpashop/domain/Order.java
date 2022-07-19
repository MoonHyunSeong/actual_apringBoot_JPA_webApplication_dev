package jpabook.jpashop.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders") // order로 적지 않는 이유는 디비에 내장된 이름이 있어 충돌위험때문에 orders로 보통 적는다고 한다.
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // 다대일 관계를 의미하는 애노테이션. 멤버로 가면 onetomany처럼 각각 앞서 설계한 내용에 맞게 적어준다.
    @JoinColumn(name = "member_id")
    private Member member;

    //JPQL select o From order o; -> SQL select * from order 이런 문제(n+1(order)) 발생하기 때문에
    // lazy -> 모든 연관관계는 지연로딩으로 설정을 해줘야한다.

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate; // 주문시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // 주문 상태 [ORDER, CANCEL]


    //== 연관관계 메서드 ==//
    // 양방향일 때 사용
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }




}
