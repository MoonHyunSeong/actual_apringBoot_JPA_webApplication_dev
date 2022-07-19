package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();
    // 하이버네이트가 한번 감싸서 지지고 볶고 하기 때문에 필드에서 바로 초기화 하는 것이 안전하다.
    // 추후 다른 식으로 초기화 할 일이 있다면 실행 이후 내용이 변경할 수 없도록 설정하면 된다.
}
