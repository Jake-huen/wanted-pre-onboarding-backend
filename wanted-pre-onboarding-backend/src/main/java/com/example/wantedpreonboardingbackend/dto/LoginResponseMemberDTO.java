package com.example.wantedpreonboardingbackend.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class LoginResponseMemberDTO {

    @ApiModelProperty(value = "사용자 이메일", example = "tae77777@naver.com", required = true)
    private String email;

    @ApiModelProperty(value = "타입", example = "Bearer", required = true)
    private String grantType;

    @ApiModelProperty(value = "jwt 토큰",required = true)
    private String accessToken;
}
