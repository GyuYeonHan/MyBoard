package spring.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.board.Repository.MemberRepository;
import spring.board.Service.MemberService;
import spring.board.domain.Member;
import spring.board.domain.Post;

import javax.annotation.PostConstruct;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping(value = "/create")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "member/memberCreateForm";
    }

    @PostMapping(value = "/create")
    public String create(@Valid MemberForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "member/memberCreateForm";
        }

        Member member = createMember(form);
        memberService.join(member);

        return "redirect:/board";
    }

    private Member createMember(MemberForm form) {
        return Member.builder()
                .username(form.getUsername())
                .password(form.getPassword())
                .build();
    }
}
