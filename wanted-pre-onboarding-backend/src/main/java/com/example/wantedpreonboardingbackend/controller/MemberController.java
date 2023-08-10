package com.example.wantedpreonboardingbackend.controller;

import com.example.wantedpreonboardingbackend.domain.Member;
import com.example.wantedpreonboardingbackend.dto.RequestMemberDto;
import com.example.wantedpreonboardingbackend.dto.LoginResponseMemberDto;
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
    public LoginResponseMemberDto login(@RequestBody RequestMemberDto requestMemberDto) {
        return memberService.login(requestMemberDto.getEmail(), requestMemberDto.getPassword());
    }

    @PostMapping("signup")
    public String signup(@Valid @RequestBody RequestMemberDto requestMemberDto) {
        return memberService.signup(requestMemberDto.getEmail(), requestMemberDto.getPassword());
    }
}
