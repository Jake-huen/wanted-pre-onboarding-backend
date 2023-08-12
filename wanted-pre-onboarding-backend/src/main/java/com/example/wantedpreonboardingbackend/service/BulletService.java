package com.example.wantedpreonboardingbackend.service;

import com.example.wantedpreonboardingbackend.domain.Bullet;
import com.example.wantedpreonboardingbackend.domain.Member;
import com.example.wantedpreonboardingbackend.dto.BulletDTO;
import com.example.wantedpreonboardingbackend.dto.BulletNewResponseDTO;
import com.example.wantedpreonboardingbackend.dto.MemberDTO;
import com.example.wantedpreonboardingbackend.repository.BulletRepository;
import com.example.wantedpreonboardingbackend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BulletService {

    private final BulletRepository bulletRepository;
    private final MemberService memberService;
    private final MemberRepository memberRepository;

    public BulletNewResponseDTO newBullet(HttpServletRequest request, BulletDTO bulletDTO) {
        MemberDTO memberDto = memberService.checkMember(request);
        Optional<Member> member = memberRepository.findByEmail(memberDto.getEmail());
        Bullet bullet = Bullet.builder()
                .title(bulletDTO.getTitle())
                .content(bulletDTO.getContent())
                .member(member.get())
                .build();
        return new BulletNewResponseDTO(bulletDTO.getTitle(), bulletDTO.getContent(), memberDto.getEmail());
    }
}
