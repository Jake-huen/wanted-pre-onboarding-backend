package com.example.wantedpreonboardingbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class LoginResponseMemberDTO {
    private String email;
    private String grantType;
    private String accessToken;
}
