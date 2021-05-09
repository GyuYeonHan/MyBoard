package spring.board.Repository;

import org.springframework.stereotype.Repository;
import spring.board.domain.Comment;
import spring.board.domain.Writing;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

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

    public List<Comment> findByWriting(Long writingId) {
        return em.createQuery("select c from Comment c where c.writing.id = :id", Comment.class)
                .setParameter("id", writingId)
                .getResultList();
    }

    public void delete(Comment comment) {
        em.remove(comment);
    }
}
