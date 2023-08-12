package com.example.wantedpreonboardingbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BulletNewResponseDTO {

    private String title;
    private String content;
    private String member;
}
