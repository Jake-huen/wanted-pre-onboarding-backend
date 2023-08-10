package com.example.wantedpreonboardingbackend.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Builder
@Data
@RequiredArgsConstructor
public class TokenInfo {
    private final String grantType;
    private final String accessToken;
}
