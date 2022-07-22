package jpabook.jpashop.controller;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookForm {


    private Long id;
    // 상품에 대한 아이디를 받는다 왜냐하면 상품 수정이 있기 떄문이다.

    private String name;
    private int price;
    private int stockQuantity;

    private String author;
    private String isbn;

}
