package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    protected Address() { // 기본생성자 없이 만들면 jpa쪽에서 문제가 발생하기 때문에 생성하고,
        //아무대서나 호출하는 것을 막기 위해서 protected 로 지정해준다.
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
