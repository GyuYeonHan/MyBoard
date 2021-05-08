package spring.board.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.board.Repository.CommentRepository;
import spring.board.domain.Comment;
import spring.board.domain.Writing;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Transactional
    public Long write(Comment comment) {
        commentRepository.save(comment);
        return comment.getId();
    }

    @Transactional
    public Long modify(Comment comment) {
        commentRepository.save(comment);
        return comment.getId();
    }

    @Transactional
    public void delete(Comment comment) {
        commentRepository.delete(comment);
    }
}
