//package com.shop.entity;
//
//import com.shop.repository.MemberRepository;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityNotFoundException;
//import javax.persistence.PersistenceContext;
//
//@SpringBootTest
//@Transactional
//public class MemberTest {
//
//
//    @Autowired
//    MemberRepository memberRepository;
//
//    @PersistenceContext
//    EntityManager em;
//
//    @Test
//    @DisplayName("Auditing 테스트")
//    @WithMockUser(username = "test",roles = "USER")
//    public void auditinTest(){
//        Member newMember = new Member();
//        memberRepository.save(newMember);
//
//        em.flush();
//        em.clear();
//
//        Member member = memberRepository.findById(newMember.getId())
//                .orElseThrow(EntityNotFoundException::new);
//
//        System.out.println("register time : " + member.getRegTime());
//        System.out.println("update Time : " + member.getUpdateTime());
//        System.out.println("create member : " + member.getCreatedBy());
//        System.out.println("update member : " + member.getModifiedBy());
//    }
//
//}
