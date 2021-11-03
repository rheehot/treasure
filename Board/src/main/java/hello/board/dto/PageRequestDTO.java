package hello.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Builder
@AllArgsConstructor
@Data
public class PageRequestDTO {
    //화면에서 전달되는 page 파라미터 및 size 파라미터를 수집하는 역할.
    //JPA를 이용하는 경우, 페이지 번호가 0부터 시작하기 때문에 1페이지의 경우 0 이 될 수 있도록 작성.

    private int page;
    private int size;
    //검색 조건, 키워드 추가
    private String type;
    private String keyword;

    public PageRequestDTO() {
        this.page = 1;
        this.size = 10;
    }

    public Pageable getPageable(Sort sort) {
        return PageRequest.of(page - 1, size, sort);
    }
}
