package com.example.wantedpreonboardingbackend.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags={"붙게해주세요"})
public class HomeController {

    @GetMapping("/")
    public String mainPage(){
        return "원티드 백엔드 사전과제 서버입니다.";
    }

    @GetMapping("/home")
    public String home(){
        return "제발 붙게 해주세요";
    }
}
