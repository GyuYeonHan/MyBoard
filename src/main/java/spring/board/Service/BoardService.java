package spring.board.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.board.Repository.BoardRepository;
import spring.board.domain.Writing;

import java.util.List;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Transactional
    public Long write(Writing writing) {
        boardRepository.save(writing);
        return writing.getId();
    }

    @Transactional
    public Long modify(Writing writing) {
        boardRepository.save(writing);
        return writing.getId();
    }

    @Transactional
    public Long delete(Writing writing) {
        boardRepository.save(writing);
        return writing.getId();
    }

    public List<Writing> findWritingByName(String name) {
        return boardRepository.findByName(name);
    }
}
