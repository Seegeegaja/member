package com.my.basicCRUD.dto;

import lombok.Data;

@Data
public class MemberDto {
    private Long id;
    private String name;
    private int age;
    private String address;

}
