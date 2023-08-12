package com.example.wantedpreonboardingbackend.service;

import com.example.wantedpreonboardingbackend.domain.Bullet;
import com.example.wantedpreonboardingbackend.domain.Member;
import com.example.wantedpreonboardingbackend.dto.BulletDTO;
import com.example.wantedpreonboardingbackend.dto.BulletResponseDTO;
import com.example.wantedpreonboardingbackend.dto.MemberDTO;
import com.example.wantedpreonboardingbackend.repository.BulletRepository;
import com.example.wantedpreonboardingbackend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

@Service
@RequiredArgsConstructor
public class BulletService {

    private final BulletRepository bulletRepository;
    private final MemberService memberService;
    private final MemberRepository memberRepository;

    public BulletResponseDTO newBullet(HttpServletRequest request, BulletDTO bulletDTO) {
        MemberDTO memberDto = memberService.checkMember(request);
        Optional<Member> member = memberRepository.findByEmail(memberDto.getEmail());
        Bullet bullet = Bullet.builder()
                .title(bulletDTO.getTitle())
                .content(bulletDTO.getContent())
                .member(member.get())
                .build();
        bulletRepository.save(bullet);
        return new BulletResponseDTO(bulletDTO.getTitle(), bulletDTO.getContent(), memberDto.getEmail());
    }

    public List<BulletResponseDTO> getAllBullets(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Bullet> bulletPage = bulletRepository.findAll(pageable);

        List<Bullet> bulletList = bulletPage.getContent();

        List<BulletResponseDTO> bulletResponseDTOList = bulletList.stream()
                .map(bullet -> new BulletResponseDTO(
                        bullet.getTitle(),
                        bullet.getContent(),
                        bullet.getMember().getEmail()
                ))
                .collect(Collectors.toList());

        return bulletResponseDTOList;
    }

    public BulletResponseDTO getBullet(@RequestParam Long id) {
        Bullet findBullet = bulletRepository.findById(id).get();
        return new BulletResponseDTO(findBullet.getTitle(), findBullet.getContent(), findBullet.getMember().getEmail());
    }
}
