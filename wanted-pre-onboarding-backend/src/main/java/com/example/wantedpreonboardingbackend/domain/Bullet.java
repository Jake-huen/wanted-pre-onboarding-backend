package com.example.wantedpreonboardingbackend.domain;

import com.example.wantedpreonboardingbackend.dto.BulletDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@RequiredArgsConstructor
@Getter
public class Bullet {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Builder
    public Bullet(String title, String content, Member member){
        this.title = title;
        this.content = content;
        this.member = member;
    }

    public void update(BulletDTO bulletDTO) {
        this.title = bulletDTO.getTitle();
        this.content = bulletDTO.getContent();
    }
}
