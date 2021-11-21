package com.memo.repository;

import com.memo.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {

    List<Memo> findByTitle(String title);

    List<Memo> findByTitleOrContent(String title, String content);

    
}
