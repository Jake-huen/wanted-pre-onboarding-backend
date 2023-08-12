package com.example.wantedpreonboardingbackend.dto;


import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
@Builder
public class RequestMemberDTO {
    @Email(message = "이메일 형식이 틀렸습니다")
    private String email;

    @Size(min = 8)
    private String password;
}
