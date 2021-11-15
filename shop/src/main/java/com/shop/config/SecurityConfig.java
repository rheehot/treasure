package com.shop.config;

import com.shop.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MemberService memberService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http 요청에 대한 보안을 설정.
        //페이지 권한 설정, 로그인 페이지 설정, 로그아웃 메소드 등에 대한 설정을 작성.

        http.formLogin()
                .loginPage("/members/login") // 로그인 페이지 URL 생성
                .defaultSuccessUrl("/") // 로그인 시 이동할 URL 설정
                .usernameParameter("email") // 로그인 시 사용할 파라미터 이름으로 email 지정
                .failureUrl("/members/login/error") // 로그인 실패 시 이동할 URL 설정
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout")) // 로그아웃 URL 설정
                .logoutSuccessUrl("/"); // 로그아웃 성공 시 이동할 URL 설정

        http.authorizeRequests() //시큐리티 처리에 HttpSetvletRequest를 이용한다는 의미.
                .mvcMatchers("/", "/members/login", "/members/**", "/item/**", "/images/**", "/main/*", "/board/**", "/about/**","/call/**").permitAll() //모든 사용자가 접근 가능.
                .mvcMatchers("/admin/**").hasRole("ADMIN") //ADMIN 접근 가능.
                .anyRequest().authenticated(); //설정해준 경로 외 나머지 경로는 모두 인증을 요구.

        http.exceptionHandling() //인증되지 않은 사용자가 리소스 접근시 수행하는 핸들러 등록.
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/banner_img.jpeg"); //인증 무시
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 스프링 세큐리티에서 인증은 AuthenticationManager를 통해 이루어지며,
        // AuthenticationManagerBuilder가 AuthenticationManager를 생성한다.
        // userDetailService를 구현하고 있는 객체로 memberService를 지정해주며, 비밀번호 암호화를 위해 passwordEncoder를 지정.
        auth.userDetailsService(memberService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        //비밀번호를 암호화
        return new BCryptPasswordEncoder();
    }
}
