package com.example.wantedpreonboardingbackend.controller;

import com.example.wantedpreonboardingbackend.domain.Member;
import com.example.wantedpreonboardingbackend.dto.RequestMemberDTO;
import com.example.wantedpreonboardingbackend.dto.LoginResponseMemberDTO;
import com.example.wantedpreonboardingbackend.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("login")
    public LoginResponseMemberDTO login(@RequestBody RequestMemberDTO requestMemberDto) {
        return memberService.login(requestMemberDto.getEmail(), requestMemberDto.getPassword());
    }

    @PostMapping("signup")
    public Member signup(@Valid @RequestBody RequestMemberDTO requestMemberDto) {
        return memberService.signup(requestMemberDto.getEmail(), requestMemberDto.getPassword());
    }
}
