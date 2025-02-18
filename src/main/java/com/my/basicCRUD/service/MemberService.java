package com.my.basicCRUD.service;

import com.my.basicCRUD.dto.MemberDto;
import com.my.basicCRUD.entity.Member;
import com.my.basicCRUD.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {
    @Autowired
    MemberRepository memberRepository;

    public List<MemberDto> findAllMembers() {
        List<Member> memberList = memberRepository.findAll();
        System.out.println(memberList);
//        List<MemberDto> dtoList = new ArrayList<>();
        //Entity class-->DTO class type으로 변경
        return memberList.stream().map(MemberDto::fromEntity).toList();
//        for (int i = 0; i < memberList.size(); i++) {
//            MemberDto dto = new MemberDto();
//            dto.setId(memberList.get(i).getMemberId());
//            dto.setName(memberList.get(i).getName());
//            dto.setAge(memberList.get(i).getAge());
//            dto.setAddress(memberList.get(i).getAddress());
//            System.out.println("for로 서비스에서 옮겨 가져오는것"+dto);
//            dtoList.add(dto);
//        }

    }
    public void saveMember(MemberDto memberDto) {
        //DTO -> Entity 로 변화
        Member member = MemberDto.fromDto(memberDto);
        // 정장요청
        memberRepository.save(member);
    }

    public void deleteById(Long id) {
        memberRepository.deleteById(id);
    }

    public MemberDto findByid(Long id) {
        //id로 디비에서 검색하기
        Member member = memberRepository.findById(id).orElse(null);
        //DTO로 변환
        if (!ObjectUtils.isEmpty(member)) {
            return MemberDto.fromEntity(member);
        } else {
            return null;
        }
    }

    public void updateMember(MemberDto dto) {
        //dto로 받았으니까 Entity로 변환
        Member member = MemberDto.fromDto(dto);
        //수정처리
        // save() : 해당ID가 존재 하면 수정 , 없으면 입력
        memberRepository.save(member);
    }
//DTO에 있는 List 를 Entity로 바꿔서 검색 하는것
    public List<MemberDto> searchName(String keyword) {
        return memberRepository.searchName(keyword).stream().map(x -> MemberDto.fromEntity(x)).toList();
    }

    public List<MemberDto> searchAddress(String keyword) {
        List<Member> memberList = memberRepository.searchAddress(keyword);
        List<MemberDto> dtoList = new ArrayList<>();
        for (int i = 0; i < memberList.size(); i++) {
            MemberDto dto = new MemberDto(
                    memberList.get(i).getMemberId(),
                    memberList.get(i).getName(),
                    memberList.get(i).getAge(),
                    memberList.get(i).getAddress()
            );
            dtoList.add(dto);
        }
        return dtoList;
    }

    public List<MemberDto> searchAll() {
        return memberRepository.searchAll().stream().map(MemberDto::fromEntity).toList();
    }
}
