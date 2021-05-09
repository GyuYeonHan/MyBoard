package spring.board.Service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import spring.board.Repository.BoardRepository;
import spring.board.domain.Category;
import spring.board.domain.Writing;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BoardServiceTest {

    @Autowired BoardService boardService;
    @Autowired BoardRepository boardRepository;

    @Test
    void 글쓰기() {
        //given
        Writing writing = new Writing();
        writing.setHeader("테스트");
        writing.setContent("이것은 테스트입니다.");

        //when
        Long id = boardService.write(writing);

        //then
        Assertions.assertThat(writing).isEqualTo(boardRepository.findOne(id));
    }

    @Test
    void 글수정() {
        //given
        Writing writing = new Writing();
        writing.setHeader("테스트");
        writing.setContent("이것은 테스트입니다.");
        Long id = boardService.write(writing);

        //when
        writing.setContent("변경된 내용입니다.");
        boardService.modify(writing);

        //then
        Assertions.assertThat(boardRepository.findOne((id)).getContent()).isEqualTo("변경된 내용입니다.");
    }

    @Test
    void 글삭제() {
        //given
        Writing writing = new Writing();
        writing.setHeader("테스트");
        writing.setContent("이것은 테스트입니다.");
        Long id = boardService.write(writing);

        Writing findWriting = boardRepository.findOne((id));

        //when
        boardService.delete(findWriting);

        //then
        Assertions.assertThat(boardRepository.findOne(id)).isNull();
    }

    @Test
    void findWritingByName() {
    }
}