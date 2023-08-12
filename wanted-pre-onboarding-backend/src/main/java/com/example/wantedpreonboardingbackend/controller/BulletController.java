package com.example.wantedpreonboardingbackend.controller;

import com.example.wantedpreonboardingbackend.dto.BulletDTO;
import com.example.wantedpreonboardingbackend.dto.BulletResponseDTO;
import com.example.wantedpreonboardingbackend.service.BulletService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bullet")
public class BulletController {

    private final BulletService bulletService;

    @PostMapping("/new")
    public BulletResponseDTO newBullet(HttpServletRequest request, @RequestBody BulletDTO bulletDTO){
        return bulletService.newBullet(request, bulletDTO);
    }

    @GetMapping("/all")
    public List<BulletResponseDTO> getAllBullets(@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                              @RequestParam(value = "pageSize", defaultValue = "3", required = false) int pageSize){
        return bulletService.getAllBullets(pageNo, pageSize);
    }

    @GetMapping()
    public BulletResponseDTO getBullet(Long id){
        return bulletService.getBullet(id);
    }

    // 과제 6 : 특정 게시글 수정

    // 과제 7 : 특정 게시글 삭제
}
