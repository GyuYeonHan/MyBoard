package spring.board.Service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import spring.board.Repository.CommentRepository;
import spring.board.domain.Comment;
import spring.board.domain.Post;

@SpringBootTest
@Transactional
class CommentServiceTest {

    @Autowired CommentService commentService;
    @Autowired CommentRepository commentRepository;

    @Test
    public void 댓글작성() {
        //given
        Post post = Post.builder()
                .title("테스트")
                .content("이것은 테스트입니다.")
                .build();

        Comment comment = new Comment();
        comment.setContent("테스트 댓글입니다.");
        comment.setPost(post);

        //when
        Long id = commentService.write(comment);

        //then
        Assertions.assertThat(comment).isEqualTo(commentRepository.findOne(id));
    }

    @Test
    public void 댓글수정() {
        //given
        Post post = Post.builder()
                .title("테스트")
                .content("이것은 테스트입니다.")
                .build();

        Comment comment = new Comment();
        comment.setContent("테스트 댓글입니다.");
        comment.setPost(post);
        Long id = commentService.write(comment);

        //when
        comment.setContent("변경된 댓글입니다.");
        commentService.modify(comment);

        //then
        Assertions.assertThat(commentRepository.findOne((id)).getContent()).isEqualTo("변경된 댓글입니다.");

    }

    @Test
    public void 댓글삭제() {
        //given
        Post post = Post.builder()
                .title("테스트")
                .content("이것은 테스트입니다.")
                .build();

        Comment comment = new Comment();
        comment.setContent("테스트 댓글입니다.");
        comment.setPost(post);
        Long id = commentService.write(comment);

        //when
        commentService.delete(comment);

        //then
        Assertions.assertThat(commentRepository.findOne(id)).isNull();
    }

}