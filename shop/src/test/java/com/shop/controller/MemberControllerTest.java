//package com.shop.controller;
//
//import com.shop.dto.MemberFormDto;
//import com.shop.entity.Member;
//import com.shop.service.MemberService;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@Transactional
//public class MemberControllerTest {
//
//    @Autowired
//    private MemberService memberService;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    PasswordEncoder passwordEncoder;
//
//    //회원 등록 메소드
//    public Member createMember(String email, String password) {
//        MemberFormDto memberFormDto = new MemberFormDto();
//        memberFormDto.setEmail(email);
//        memberFormDto.setName("홍길동");
//        memberFormDto.setAddress("서울시 마포구 합정동");
//        memberFormDto.setPassword(password);
//        Member member = Member.createMember(memberFormDto, passwordEncoder);
//        return memberService.saveMember(member);
//    }
//
//    @Test
//    @DisplayName("로그인 성공 테스트")
//    public void loginSuccessTest() throws Exception {
//        String email = "test@test.com";
//        String password = "12345678";
//        this.createMember(email, password);
//        mockMvc.perform(formLogin().userParameter("email")
//                        .loginProcessingUrl("/members/login") //회원가입 메소드를 실행 후 가입된 회원 정보로 로그인이 되는지 테스트
//                        .user(email).password(password))
//                .andExpect(SecurityMockMvcResultMatchers.authenticated()); //로그인이 성공하여 인증되면, 테스트코드 통과.
//    }
//
//    @Test
//    @DisplayName("로그인 실패 테스트")
//    public void loginFailTest() throws Exception {
//        String email = "test@test.com";
//        String password = "12345678";
//        mockMvc.perform(formLogin().userParameter("email")
//                        .loginProcessingUrl("/members/login")
//                        .user(email).password("12345"))
//                .andExpect(SecurityMockMvcResultMatchers.unauthenticated()); //회원가입은 정상적으로 진행되었지만 비밀번호를 다르게 입력.
//    }
//
//}
