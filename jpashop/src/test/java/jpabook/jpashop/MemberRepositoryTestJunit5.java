package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)  //@RunWith(SpringRunner.class) -> Junit4에서는 이거 쓰지만 5에서는 extendWith으로 바뀌었다.
@SpringBootTest
class MemberRepositoryTestJunit5 {

    @Autowired
    MemberRepository memberRepository;

    @Test
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
     }


}