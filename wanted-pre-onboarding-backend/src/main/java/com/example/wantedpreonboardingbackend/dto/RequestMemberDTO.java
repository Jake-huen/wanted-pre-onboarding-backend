package com.example.wantedpreonboardingbackend.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
@Builder
public class RequestMemberDTO {
    @Email(message = "이메일 형식이 틀렸습니다")
    @ApiModelProperty(value = "사용자 이메일", example = "tae77777@naver.com", required = true)
    private String email;

    @Size(message = "비밀번호는 8자리 이상이어야 합니다.", min = 8)
    @ApiModelProperty(value = "사용자 비밀번호", example = "12345678", required = true)
    private String password;
}
