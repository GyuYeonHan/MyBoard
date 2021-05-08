package spring.board.Repository;

import org.springframework.stereotype.Repository;
import spring.board.domain.Member;
import spring.board.domain.Writing;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

}
