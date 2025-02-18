package com.my.basicCRUD.controller;

import com.my.basicCRUD.dto.MemberDto;
import com.my.basicCRUD.entity.Member;
import com.my.basicCRUD.service.MemberService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/member")
@Slf4j
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/insertForm")
    public String insertFormView(Model model) {
        model.addAttribute("memberDto", new MemberDto());
        return "insertMember";
    }

    @PostMapping("/insert")
    public String insert(
            @Valid MemberDto memberDto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        //Validation Check 진행
        if (bindingResult.hasErrors()) {
            //오류 있을때
            log.info("=============== validationError");
            return "insertMember";
        }
        System.out.println("insert 에서 찍혀야한느것" + memberDto);
        //받은 Dto를 서비스에 넘겨주고 저장요청한다
        memberService.saveMember(memberDto);
        redirectAttributes.addFlashAttribute("msg", "신규데이터가 입력되었습니다");
        return "redirect:/member/view";
    }

    @GetMapping("view")
    public String showMember(Model model) {
        List<MemberDto> memberList = memberService.findAllMembers();
        System.out.println(memberList);
        model.addAttribute("list", memberList);
        return "showMember";
    }

    @GetMapping("delete/{id}")
    public String deleteId(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {

        memberService.deleteById(id);
        redirectAttributes.addFlashAttribute("msg", "선택한 자료가 삭제되었습니다");
        return "redirect:/member/view";
    }

    @GetMapping("update/{id}")
    public String updateFormView(@PathVariable("id") Long id, Model model) {
        MemberDto dto = memberService.findByid(id);
        log.info("===========dto : " + dto);
        model.addAttribute("member", dto);
        return "updateMember";
    }

    @PostMapping("update")
    public String update(@Valid @ModelAttribute("member") MemberDto memberDto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "updateMember";
        }
        //수정요청
        log.info("dto update : " + memberDto);
        memberService.updateMember(memberDto);
        //메시지 전송
        redirectAttributes.addFlashAttribute("msg", "정상적으로 수정되었습니다");
        return "redirect:/member/view";
    }

    @GetMapping("search")
    public String search(@RequestParam("type") String type, @RequestParam("keyword") String keyword , Model model) {
        log.info("type : " +type + ",,keyword : " + keyword);
        //type:
        //1. 공백 : 전체 자료를 ID에 대해서 오름차순이면 - 출력
        // select * from member where name like '%가자%' order by desc
        //2. name : 이름 일부 검색
        //3. address : 주소 일부검색
        //keyword :
        //1. 공백 : 전체이름 또는 주소 검색
        //2. 해당 키워드로 검색
        List<MemberDto> searchList = new ArrayList<>();
        switch (type) {
            case "name" :
                searchList = memberService.searchName(keyword);
                break;
            case "address" :
                searchList = memberService.searchAddress(keyword);
                break;
            default:
                searchList = memberService.searchAll();
                break;
        }
        model.addAttribute("list", searchList);
        return "showMember";
    }
}
