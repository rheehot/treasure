package com.shop.repository;

import com.shop.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // 회원가입시 중복된 회원이 검사하기 위한 이메일로 회원을 검사할 수 있도록.
    Member findByEmail(String email);
}
