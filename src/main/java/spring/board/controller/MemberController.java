package spring.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.board.Repository.MemberRepository;
import spring.board.domain.Member;

import javax.annotation.PostConstruct;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping
    public String createForm() {
        return "member/memberCreateForm";
    }

    @PostMapping
    public String create() {
        return "";
    }

}
