package jpabook.jpashop;


import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    public Long save(Member member) {
        em.persist(member);
        return member.getId(); // command query를 분리해라 원칙으로 ID정도만 조회하도록 설계하신다.
    }

    public Member find(Long id) { // jpa
        return em.find(Member.class, id);
    }

}
