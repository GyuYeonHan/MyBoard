package spring.board.Service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import spring.board.Repository.BoardRepository;
import spring.board.domain.Post;

@SpringBootTest
@Transactional
class BoardServiceTest {

    @Autowired BoardService boardService;
    @Autowired BoardRepository boardRepository;

    @Test
    void 글쓰기() {
        //given
        Post post = Post.builder()
                .title("테스트")
                .content("이것은 테스트입니다.")
                .build();

        //when
        Long id = boardService.write(post);

        //then
        Assertions.assertThat(post).isEqualTo(boardRepository.findOne(id));
    }

    @Test
    void 글수정() {
        //given
        Post post = Post.builder()
                .title("테스트")
                .content("이것은 테스트입니다.")
                .build();
        Long id = boardService.write(post);

        //when
        post.setContent("변경된 내용입니다.");
        boardService.modify(post);

        //then
        Assertions.assertThat(boardRepository.findOne((id)).getContent()).isEqualTo("변경된 내용입니다.");
    }

    @Test
    void 글삭제() {
        //given
        Post post = Post.builder()
                .title("테스트")
                .content("이것은 테스트입니다.")
                .build();

        Long id = boardService.write(post);
        Post findPost = boardRepository.findOne((id));

        //when
        boardService.delete(findPost);

        //then
        Assertions.assertThat(boardRepository.findOne(id)).isNull();
    }

    @Test
    void findWritingByName() {
    }
}