package com.my.basicCRUD.service;

import com.my.basicCRUD.dto.MemberDto;
import com.my.basicCRUD.entity.Member;
import com.my.basicCRUD.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {
    @Autowired
    MemberRepository memberRepository;

    public List<Member> findAllMembers() {
        List<Member> memberList = memberRepository.findAll();
        List<MemberDto> dtoList = new ArrayList<>();
        //Entity class-->DTO class type으로 변경
        for (int i = 0; i < memberList.size(); i++) {
            Member member = memberList.get(i);

        }
        return memberList;
    }
}
