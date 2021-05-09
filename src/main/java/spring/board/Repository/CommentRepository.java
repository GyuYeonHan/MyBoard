package spring.board.Repository;

import org.springframework.stereotype.Repository;
import spring.board.domain.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CommentRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Comment comment) {
        if (comment.getId() == null) {
            em.persist(comment);
        } else {
            em.merge(comment);
        }
    }

    public Comment findOne(Long id) {
        return em.find(Comment.class, id);
    }

    public void delete(Comment comment) {
        em.remove(comment);
    }
}
