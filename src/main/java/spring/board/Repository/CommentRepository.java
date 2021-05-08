package spring.board.Repository;

import org.springframework.stereotype.Repository;
import spring.board.domain.Writing;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CommentRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Writing writing) {
        if (writing.getId() == null) {
            em.persist(writing);
        } else {
            em.merge(writing);
        }
    }
}
