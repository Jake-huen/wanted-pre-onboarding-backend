package com.example.wantedpreonboardingbackend.service;

import com.example.wantedpreonboardingbackend.config.JwtTokenProvider;
import com.example.wantedpreonboardingbackend.domain.Member;
import com.example.wantedpreonboardingbackend.dto.LoginResponseMemberDTO;
import com.example.wantedpreonboardingbackend.dto.MemberDTO;
import com.example.wantedpreonboardingbackend.dto.TokenInfo;
import com.example.wantedpreonboardingbackend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public LoginResponseMemberDTO login(String email, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);

        System.out.println("authenticationToken = " + authenticationToken);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        System.out.println("authentication = " + authentication);
        Optional<Member> member = memberRepository.findByEmail(email);
        TokenInfo tokenInfo = jwtTokenProvider.generateToken(member.get());

        return new LoginResponseMemberDTO(email, tokenInfo.getGrantType(), tokenInfo.getAccessToken());
    }

    public Member signup(String email, String password) {
        Member member = Member.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .build();
        System.out.println("member = " + member);
        Member savedMember = memberRepository.save(member);

        return savedMember;
    }

    public MemberDTO checkMember(HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);
        token = jwtTokenProvider.getToken(token);
        String email = jwtTokenProvider.getEmail(token);
        Optional<Member> member = memberRepository.findByEmail(email);
        if(member.isPresent()){
            MemberDTO memberDto = MemberDTO.builder()
                    .email(member.get().getEmail())
                    .build();
            return memberDto;
        }else{
            throw new RuntimeException("존재하지 않는 이메일입니다.");
        }
    }
}
