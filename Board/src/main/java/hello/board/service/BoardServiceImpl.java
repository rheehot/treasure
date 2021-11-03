package hello.board.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import hello.board.dto.BoardDTO;
import hello.board.dto.PageRequestDTO;
import hello.board.dto.PageResultDTO;
import hello.board.entity.Board;
import hello.board.entity.Member;
import hello.board.entity.QBoard;
import hello.board.repository.BoardRepository;
import hello.board.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.function.Function;

@Service
@Slf4j
@RequiredArgsConstructor //의존성 자동 주입
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository; //반드시 final로 선언
    private final ReplyRepository replyRepository;


    @Override
    public Long register(BoardDTO dto) {
        log.info("DTO = {}", dto);

        Board board = dtoToEntity(dto);

        log.info("entity = {}", board);

        boardRepository.save(board);

        return board.getBno();
    }


    @Override
    public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {

        log.info("pageRequestDTO = {}", pageRequestDTO);

        Function<Object[], BoardDTO> fn = (en -> entityToDTO((Board) en[0], (Member) en[1], (Long) en[2]));

        // Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageRequestDTO.getPageable(Sort.by("bno").descending()));

        Page<Object[]> result = boardRepository.searchPage(
                pageRequestDTO.getType(),
                pageRequestDTO.getKeyword(),
                pageRequestDTO.getPageable(Sort.by("bno").descending()));

        return new PageResultDTO<>(result, fn);

    }

    @Override
    public BoardDTO get(Long bno) {

        Object result = boardRepository.getBoardByBno(bno);

        Object[] arr = (Object[]) result;

        return entityToDTO((Board) arr[0], (Member) arr[1], (Long) arr[2]);

    }

    @Override
    public void modify(BoardDTO boardDTO) {
        Board board = boardRepository.getOne(boardDTO.getBno());

        board.changeTitle(boardDTO.getTitle());
        board.changeContent(boardDTO.getContent());

        boardRepository.save(board);
    }


    @Transactional
    @Override
    public void removeWithReplies(Long bno) {

        replyRepository.deleteByBno(bno);
        boardRepository.deleteById(bno);

    }

    // PageRequestDTO를 파라미터로 받아서 검색 조건(type)이 있는 경우,
    // conditionBuilder 변수를 생성해서 각 검색 조건을 or로 연결해서 처리.
    // 검색 조건이 없다면, bno > 0 으로만 생성.
    private BooleanBuilder getSearch(PageRequestDTO requestDTO) {
        //Querydsl 처리
        String type = requestDTO.getType();

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        QBoard qBoard = QBoard.board;

        String keyword = requestDTO.getKeyword();

        BooleanExpression expression = qBoard.bno.gt(0L); // gno > 0 조건만 생성

        booleanBuilder.and(expression);

        if (type == null || type.trim().length() == 0) { //조건 검색이 없는 경우,
            return booleanBuilder;
        }

        //검색 조건을 작성.
        BooleanBuilder conditionBuilder = new BooleanBuilder();

        if (type.contains("t")) {
            conditionBuilder.or(qBoard.title.contains(keyword));
        }
        if (type.contains("c")) {
            conditionBuilder.or(qBoard.content.contains(keyword));
        }
//        if (type.contains("w")) {
//            conditionBuilder.or(qBoard.writer.contains(keyword));
//        }

        //모든 조건 통합
        booleanBuilder.and(conditionBuilder);

        return booleanBuilder;
    }
}
