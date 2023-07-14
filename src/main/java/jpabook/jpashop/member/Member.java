package jpabook.jpashop.member;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long Id;

    private String name;

    @Embedded
    private Address address;

    // order table에 있는 member필드에 의해 연관관계가 매핑이 된 것 -> 읽기 전용으로 바뀜 orders에 뭘 넣는다고 해서
    // 디비상의 foreign key의 값이 바뀌거나 하지는 않는다
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();
}
