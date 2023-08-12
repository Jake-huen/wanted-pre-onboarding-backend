package com.example.wantedpreonboardingbackend.domain;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@RequiredArgsConstructor
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
}
