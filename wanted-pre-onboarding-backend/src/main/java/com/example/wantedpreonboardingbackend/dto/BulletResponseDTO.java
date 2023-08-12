package com.example.wantedpreonboardingbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class BulletResponseDTO {

    private String title;
    private String content;
    private String member;

    @Builder
    public BulletResponseDTO(String title, String content){
        this.title = title;
        this.content = content;
    }
}
