package spring.board.Repository;

import org.springframework.stereotype.Repository;
import spring.board.domain.Post;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class BoardRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Post post) {
        if (post.getId() == null) {
            em.persist(post);
        } else {
            em.merge(post);
        }
    }

    public Post findOne(Long id) {
        return em.find(Post.class, id);
    }

    public List<Post> findAll() {
        return em.createQuery("select p from Post p", Post.class)
                .getResultList();
    }

    public List<Post> findByName(String name) {
        return em.createQuery("select p from Post p where p.member.name = :name", Post.class)
                .setParameter("name", name)
                .getResultList();
    }

    public void delete(Post post) {
        em.remove(post);
    }
}
