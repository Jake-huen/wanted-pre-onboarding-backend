package com.example.wantedpreonboardingbackend.controller;

import com.example.wantedpreonboardingbackend.dto.BulletDTO;
import com.example.wantedpreonboardingbackend.dto.BulletResponseDTO;
import com.example.wantedpreonboardingbackend.service.BulletService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.hibernate.mapping.Join;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bullet")
@Api(tags={"게시글 API"})
public class BulletController {

    private final BulletService bulletService;

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "게시글 생성", response = Join.class)
    public BulletResponseDTO newBullet(HttpServletRequest request, @RequestBody BulletDTO bulletDTO){
        return bulletService.newBullet(request, bulletDTO);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "게시글 모두 조회", response = Join.class)
    public List<BulletResponseDTO> getAllBullets(@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                              @RequestParam(value = "pageSize", defaultValue = "3", required = false) int pageSize){
        return bulletService.getAllBullets(pageNo, pageSize);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "특정 게시글 조회", response = Join.class)
    public BulletResponseDTO getBullet(@RequestParam Long id){
        return bulletService.getBullet(id);
    }

    @PostMapping("/edit")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "특정 게시글 수정", response = Join.class)
    public String editBullet(HttpServletRequest request, @RequestParam Long id, @RequestBody BulletDTO bulletDTO){
        return bulletService.editBullet(request, id, bulletDTO);
    }

    @PostMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "특정 게시글 삭제", response = Join.class)
    public String deleteBullet(HttpServletRequest request, @RequestParam Long id) {
        return bulletService.deleteBullet(request, id);
    }
}
