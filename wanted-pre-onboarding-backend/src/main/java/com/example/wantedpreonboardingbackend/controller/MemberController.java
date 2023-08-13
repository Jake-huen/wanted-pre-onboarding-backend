package com.example.wantedpreonboardingbackend.controller;

import com.example.wantedpreonboardingbackend.domain.Member;
import com.example.wantedpreonboardingbackend.dto.RequestMemberDTO;
import com.example.wantedpreonboardingbackend.dto.LoginResponseMemberDTO;
import com.example.wantedpreonboardingbackend.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.hibernate.mapping.Join;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
@Api(tags={"회원가입 API"})
public class MemberController {

    private final MemberService memberService;

    @PostMapping("login")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "로그인", response = Join.class)
    public LoginResponseMemberDTO login(@Valid @RequestBody RequestMemberDTO requestMemberDto) {
        return memberService.login(requestMemberDto.getEmail(), requestMemberDto.getPassword());
    }

    @PostMapping("signup")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "회원가입", response = Join.class)
    public Member signup(@Valid @RequestBody RequestMemberDTO requestMemberDto) {
        return memberService.signup(requestMemberDto.getEmail(), requestMemberDto.getPassword());
    }
}
