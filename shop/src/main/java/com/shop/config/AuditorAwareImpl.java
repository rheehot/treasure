package com.shop.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    //현재 로그인한 사용자 정보를 등록자와 수정자로 지정하기 위한 클래스.


    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = "";
        if (authentication != null) {
            userId = authentication.getName(); // 현재 로그인 한 사용자의 정보를 조회 후 사용자 이름을 등록자와 수정자로 지정.
        }
        return Optional.of(userId);
    }
}
