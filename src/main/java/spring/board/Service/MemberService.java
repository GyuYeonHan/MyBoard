package spring.board.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.board.Repository.MemberRepository;
import spring.board.domain.Member;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Long join(Member member) {
        memberRepository.save(member);
        return member.getId();
    }

    public Member find(Long id) {
        return memberRepository.findOne(id);
    }
}
