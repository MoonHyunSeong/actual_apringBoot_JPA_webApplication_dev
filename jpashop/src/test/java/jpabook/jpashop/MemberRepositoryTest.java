package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional // 이게 없으면 트랜젝션이 없어서 에러가 난다. + test에 존재하면 테스트가 끝나고 롤백해버려서 디비에 테이블만 남게된다.
    @Rollback(value = false) // <- 롤백시키지 않고 데이터가 들어갔는지 확인하고 싶다면 이런식으로 애노테이션 넣어주면 된다.
    public void testMember() throws Exception { //preference -> live template -> custom tdd+tap
        //given
        Member member = new Member();
        member.setUsername("memberA");

        //when
        Long saveId = memberRepository.save(member);
        Member findMember = memberRepository.find(saveId);

        //then
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        Assertions.assertThat(findMember).isEqualTo(member); // -> true가 나온다 같은 트랜젝션에서 진행하면 같은 영속성 하에서 같은 엔티티로
        // 식별되기 때문에 같은 엔티티로 인식된다.
        System.out.println("findMember == member " + (findMember == member));
    }
}