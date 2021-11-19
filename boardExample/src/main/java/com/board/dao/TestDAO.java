package com.board.dao;

import com.board.dto.TestDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestDAO {

    List<TestDTO> getTestData();
}
