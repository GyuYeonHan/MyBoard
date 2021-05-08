package spring.board.Repository;

import org.springframework.stereotype.Repository;
import spring.board.domain.Writing;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class BoardRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Writing writing) {
        em.persist(writing);
    }

    public Writing findOne(Long id) {
        return em.find(Writing.class, id);
    }

    public List<Writing> findAll() {
        return em.createQuery("select w from Writing w", Writing.class)
                .getResultList();
    }

    public List<Writing> findByName(String name) {
        return em.createQuery("select w from Writing w where w.member.name = :name", Writing.class)
                .setParameter("name", name)
                .getResultList();
    }
}
