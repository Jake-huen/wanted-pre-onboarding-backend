package com.example.wantedpreonboardingbackend.controller;

import com.example.wantedpreonboardingbackend.domain.Bullet;
import com.example.wantedpreonboardingbackend.dto.BulletDTO;
import com.example.wantedpreonboardingbackend.dto.BulletNewResponseDTO;
import com.example.wantedpreonboardingbackend.service.BulletService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bullet")
public class BulletController {

    private final BulletService bulletService;

    @PostMapping("/new")
    public BulletNewResponseDTO newBullet(HttpServletRequest request, @RequestBody BulletDTO bulletDTO){
        return bulletService.newBullet(request, bulletDTO);
    }

    // 과제 4 : 게시글 목록 조회
    @GetMapping("/all")
    public String getAllBullets(){
        return null;
    }

    // 과제 5 : 특정 게시글 조회
    @GetMapping()
    public String getBullet(Long id){
        return null;
    }

    // 과제 6 : 특정 게시글 수정

    // 과제 7 : 특정 게시글 삭제
}
