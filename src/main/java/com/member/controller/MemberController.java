package com.member.controller;

import com.member.model.Member;
import com.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Description;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @Description("회원가입")
    @PostMapping(path = "/members")
    public Member saveMember(@RequestBody Member member, Errors errors) throws Exception {
        if (errors.hasErrors()) {
            System.err.println(errors);
        }
        return memberService.saveMember(member);
    }

    @Description("회원정보조회")
    @GetMapping(path = "/members/{memberNo}")
    public Member getMember(@PathVariable Long memberNo) throws Exception {
        return memberService.getMember(memberNo);
    }

}
