package com.example.wantedpreonboardingbackend.filter;

import org.springframework.security.core.parameters.P;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class jwtFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // 토큰을 만들어줘야 한다. ID,PW가 정상적으로 들어와서 로그인이 완료되면, 토큰을 만들어주고 그걸 응답해준다.
        // 요청할때마다 header에 Authorization의 value로 토큰을 넘겨받는다.
        // 토큰이 넘겨오면 내가 발급해준 토큰이 맞는지 확인하면 된다. (RSA나 HS256으로 토큰 검증을 하면 된다)
        if(req.getMethod().equals("POST")){
            System.out.println("POST 요청됨");
            String headerAuth = req.getHeader("Authorization");
            System.out.println("headerAuth = " + headerAuth);

            if(headerAuth.equals("sex")){
                chain.doFilter(request, response);
            }else{
                PrintWriter printWriter = res.getWriter();
                printWriter.write("인증안됨");
            }
        }

    }
}
