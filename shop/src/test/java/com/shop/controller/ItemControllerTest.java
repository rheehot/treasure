//package com.shop.controller;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class ItemControllerTest {
//
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @Test
//    @DisplayName("상품 등록 페이지 권한 테스트")
//    @WithMockUser(username = "ADMIN", roles = "ADMIN") //유저가 로그인된 상태로 테스트를 할 수 있도록 해주는 어노테이션
//    public void itemFormTest() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/admin/item/new")) //상품 등록 페이지에 요청을 get 요청을 보내기.
//                .andDo(print()) //요청과 응답 메세지를 확인.
//                .andExpect(status().isOk()); //정상인지 확인.
//    }
//
//    @Test
//    @DisplayName("상품 등록 페이지 일반 회원 접근 테스트")
//    @WithMockUser(username = "user", roles = "USER")
//    public void itemFormNotAdminTest() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/admin/item/new"))
//                .andDo(print())
//                .andExpect(status().isForbidden()); // 상품 등록 페이지 진입 요청 시 Forbidden 예외 발생.
//    }
//
//}
