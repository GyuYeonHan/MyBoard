package spring.board.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.board.Repository.BoardRepository;
import spring.board.domain.Comment;
import spring.board.domain.Post;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final CommentService commentService;

    @Transactional
    public Long write(Post post) {
        boardRepository.save(post);
        return post.getId();
    }

    @Transactional
    public Long modify(Post post) {
        boardRepository.save(post);
        return post.getId();
    }

    @Transactional
    public void delete(Post post) {
        for (Comment comment : post.getComments()) {
            commentService.delete(comment);
        }

        boardRepository.delete(post);
    }

    public List<Post> findAllPosts() {
        return boardRepository.findAll();
    }

    public Post findOne(Long id) {
        return boardRepository.findOne(id);
    }

    public List<Post> findPostsByName(String name) {
        return boardRepository.findByName(name);
    }
}
