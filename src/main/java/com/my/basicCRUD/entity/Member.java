package com.my.basicCRUD.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;


//Entity는 데이터베이스의 테이블이다
@Entity
@Table(name = "member")
@Data
@ToString(callSuper = true)
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
    @Column(name = "member_name" , length = 100 , nullable = false)
    private String name;
    private int age;
    private String address;

}
