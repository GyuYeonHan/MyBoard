package spring.board.Service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import spring.board.Repository.CommentRepository;
import spring.board.domain.Comment;
import spring.board.domain.Writing;

@SpringBootTest
@Transactional
class CommentServiceTest {

    @Autowired CommentService commentService;
    @Autowired CommentRepository commentRepository;

    @Test
    public void 댓글작성() {
        //given
        Writing writing = new Writing();
        Comment comment = new Comment();
        comment.setContent("테스트 댓글입니다.");
        comment.setWriting(writing);

        //when
        Long id = commentService.write(comment);

        //then
        Assertions.assertThat(comment).isEqualTo(commentRepository.findOne(id));
    }

    @Test
    public void 댓글수정() {
        //given
        Writing writing = new Writing();
        Comment comment = new Comment();
        comment.setContent("테스트 댓글입니다.");
        comment.setWriting(writing);
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
        Writing writing = new Writing();
        Comment comment = new Comment();
        comment.setContent("테스트 댓글입니다.");
        comment.setWriting(writing);
        Long id = commentService.write(comment);

        //when
        commentService.delete(comment);

        //then
        Assertions.assertThat(commentRepository.findOne(id)).isNull();
    }

}