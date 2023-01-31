package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
//JPA 같은 트랙잭션안에서 같은 엔티티 즉 PK값이 같으면, 같은 영속성 컨텍스트에서 관리된다.
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    EntityManager em;

    @Test
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("jung");

        //when
        Long saveId = memberService.join(member);

        //then
        em.flush();
        Assertions.assertEquals(member, memberRepository.findOne(saveId));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("jung");

        Member member2 = new Member();
        member2.setName("jung");

        //when
        memberService.join(member1);
        //중복 회원 예외 발생 -> IllegalStateException
        memberService.join(member2);

        //then
        Assert.fail("예외가 발생해야 합니다.");
    }
}