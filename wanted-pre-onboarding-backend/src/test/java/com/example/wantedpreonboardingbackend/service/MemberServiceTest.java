package com.example.wantedpreonboardingbackend.service;

import com.example.wantedpreonboardingbackend.config.SecurityConfig;
import com.example.wantedpreonboardingbackend.domain.Member;
import com.example.wantedpreonboardingbackend.dto.RequestMemberDTO;
import com.example.wantedpreonboardingbackend.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
@DisplayName("Member Service Test")
class MemberServiceTest {

    private static final String EMAIL = "tae77777@naver.com";
    private static final String PASSWORD = "12345678";

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void beforeEach() {
        SecurityConfig securityConfig = new SecurityConfig();
        memberService = ();
    }

    @Test
    @DisplayName("비밀번호는 암호화되어야 한다.")
    void hashPassword() throws Exception {
        // given
        RequestMemberDTO requestMemberDto = createSignUpRequest();

        // when
        Member newMember = memberService.signup(requestMemberDto.getEmail(), requestMemberDto.getPassword());

        // then
        System.out.println("newUser pw = " + newMember.getPassword());
        assertThat(newMember.getPassword()).isNotEqualTo(PASSWORD);
    }

    private RequestMemberDTO createSignUpRequest() {
        return RequestMemberDTO.builder()
                .email(EMAIL)
                .password(PASSWORD)
                .build();
    }
}