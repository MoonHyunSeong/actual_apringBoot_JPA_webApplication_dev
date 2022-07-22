package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

//    @PersistenceContext // jpa가 제공하는 표준 애노테이션 -> 스프링이 엔티티 매니저를 만들어서 주입해준다.
//    @Autowired // spring을  사용하면 사용이 가능하기 때문에 lombok 애노테이션을 통해 일관성 있게 코드를 작성한다.
    private final EntityManager em;
    //그러니깐 원래는 PersistenceContext 로만 주입 할 수 있는데 스프링데이터jpa가 Autowired로 주입이 가능하게 해주기에 이런식으로 코드 작성이 가능해진다.

    public void save(Member member) {
        em.persist(member); // 영속성 컨텐츠에 멤버 주입
    }

    public Member findOne(Long id) { // -> 이렇게 하면 주입된 매니저가 멤버의 아이디를 보고 찾아서 리턴해준다.
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();

//        List<Member> result = em.createQuery("select m from Member m", Member.class)
//                .getResultList();
//        return result;
        // 원래는 이런 모양인데 옵션 + 커멘트 + n 누르면 인라인 형태로 깔끔해진다.
        // 멤버가 리스트로 저장되어 있기에 member.class에 jpql로 쿼리를 날려야만 전부 가져올 수 있다.
        // jpql은 엔티티에 대해 쿼리를 날리기에 sql과 조금 다르다.
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name =:name", Member.class)
                .setParameter("name", name)
                .getResultList();
    } // 이름으로 찾기
}
