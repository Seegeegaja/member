package com.my.basicCRUD.dto;

import com.my.basicCRUD.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
    private Long id;
    private String name;
    private int age;
    private String address;


    //Entity정보 받아서 DTO로 변환하는작업
    public static MemberDto fromEntity(Member entity) {
        return new MemberDto(
                entity.getMemberId()
                ,entity.getName()
                ,entity.getAge()
                ,entity.getAddress()
        );
    }
    //dto - > Entity
    public static Member fromDto(MemberDto dto) {
        Member member = new Member();
        member.setMemberId(dto.getId());
        member.setName(dto.getName());
        member.setAge(dto.getAge());
        member.setAddress(dto.getAddress());
        return member;
    }
}
