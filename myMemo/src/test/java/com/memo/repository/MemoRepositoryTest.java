package com.memo.repository;

import com.memo.entity.Memo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemoRepositoryTest {

    @Autowired //bean 주입
    MemoRepository memoRepository;

    @Test
    @DisplayName("메모 저장 테스트")
    public void createMemoTest() {
        Memo memo = new Memo();
        memo.setTitle("메모 제목");
        memo.setContent("메모 내용");
        memo.setRegTime(LocalDateTime.now());
        memo.setUpdateTime(LocalDateTime.now());
        Memo savedMemo = memoRepository.save(memo);
        System.out.println(savedMemo.toString());
    }


}