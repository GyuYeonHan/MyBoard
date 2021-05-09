package spring.board.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.board.Repository.BoardRepository;
import spring.board.domain.Post;

import java.util.List;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

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
