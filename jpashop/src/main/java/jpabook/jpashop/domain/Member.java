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
    @Column(name ="member_id")
    private Long id;
    private String username;

    @Embedded
    private Address address;

    //mappedBy를 적는 순간 매핑을 하는 아이가 아니다.
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();
}
