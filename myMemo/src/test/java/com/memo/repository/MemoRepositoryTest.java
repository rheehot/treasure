package com.memo.repository;

import com.memo.entity.Memo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemoRepositoryTest {

    @Autowired //bean 주입
    MemoRepository memoRepository;

    @Test
    @DisplayName("더미 데이터 추가")
    public void createMemoList() {
        for (int i = 1; i <= 10; i++) {
            Memo memo = new Memo();
            memo.setTitle("메모 제목" + i);
            memo.setContent("메모 내용" + i);
            memo.setRegTime(LocalDateTime.now());
            memo.setUpdateTime(LocalDateTime.now());

            Memo savedMemo = memoRepository.save(memo);
        }
    }


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

    @Test
    @DisplayName("메모 제목 찾기 테스트")
    public void findByTitleTest() {
        List<Memo> memoList = memoRepository.findByTitle("메모 제목1");
        for (Memo memo : memoList) {
            System.out.println(memo.toString());
        }
    }

    @Test
    @DisplayName("메모 제목 또는 내용 테스트")
    public void findByTitleOrContentTest() {
        List<Memo> memoList = memoRepository.findByTitleOrContent("메모 제목1", "메모 내용");
        for (Memo memo : memoList) {
            System.out.println(memo.toString());
        }
    }
}