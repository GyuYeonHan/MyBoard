package spring.board.Service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import spring.board.Repository.MemberRepository;
import spring.board.domain.Member;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    public void 회원가입() {
        //given
        Member member = Member.builder().username("UserA").password("1234").build();

        //when
        Long id = memberService.join(member);

        //then
        Assertions.assertThat(member).isEqualTo(memberRepository.findOne(id));
    }

    @Test
    public void 회원조회() {
        //given
        Member member = Member.builder().username("UserA").password("1234").build();
        Long id = memberService.join(member);

        //when
        Member findMember = memberService.findOne(id);

        //then
        Assertions.assertThat(member.getUsername()).isEqualTo(findMember.getUsername());

    }

}