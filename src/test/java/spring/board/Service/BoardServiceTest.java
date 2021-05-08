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
        writing.setCategory(Category.ETC);
        writing.setCreatedTime(LocalDateTime.now());
        writing.setHeader("테스트");
        writing.setContent("이것은 테스트입니다.");

        //when
        Long writedId = boardService.write(writing);

        //then
        Assertions.assertThat(writing).isEqualTo(boardRepository.findOne(writedId));
    }

    @Test
    void 글수정() {
        //given
        Writing writing = new Writing();
        writing.setCategory(Category.ETC);
        writing.setCreatedTime(LocalDateTime.now());
        writing.setHeader("테스트");
        writing.setContent("이것은 테스트입니다.");
        Long writedId = boardService.write(writing);

        writing.setContent("변경된 내용입니다.");

        //when
        boardService.modify(writing);

        //then
        Assertions.assertThat(boardRepository.findOne((writedId)).getContent()).isEqualTo("변경된 내용입니다.");
    }

    @Test
    void 글삭제() {
        //given
        Writing writing = new Writing();
        writing.setCategory(Category.ETC);
        writing.setCreatedTime(LocalDateTime.now());
        writing.setHeader("테스트");
        writing.setContent("이것은 테스트입니다.");
        Long writedId = boardService.write(writing);

        Writing findWriting = boardRepository.findOne((writedId));

        //when
        boardService.delete(findWriting);

        //then
        Assertions.assertThat(boardRepository.findOne(writedId)).isNull();
    }

    @Test
    void findWritingByName() {
    }
}