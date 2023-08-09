package com.example.wantedpreonboardingbackend.dto;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
public class TokenInfo {
    private final String grantType;
    private final String accessToken;
    private final String refreshToken;
}
