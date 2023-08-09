package com.example.wantedpreonboardingbackend.controller;

import com.example.wantedpreonboardingbackend.domain.Member;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    @PostMapping("join")
    public String join(@RequestBody Member member) {
        return null;
    }
}
