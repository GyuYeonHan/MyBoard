package spring.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.board.Service.BoardService;
import spring.board.Service.MemberService;
import spring.board.domain.Writing;
import spring.board.domain.WritingForm;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller()
@RequiredArgsConstructor
@RequestMapping("/board")
@Slf4j
public class BoardController {

    private final BoardService boardService;
    private final MemberService memberService;

    @GetMapping
    public String boardMain(Model model) {
        List<Writing> writings = boardService.findAllWritings();
        model.addAttribute("writings", writings);
        return "board/board";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("writingForm", new WritingForm());
        return "board/writingCreateForm";
    }

    @PostMapping("/create")
    public String create(WritingForm form) {
        Writing writing = createWriting(form);
        boardService.write(writing);

        return "redirect:/board";
    }

    @GetMapping("/{writingId}")
    public String viewWriting(@PathVariable Long writingId, Model model) {
        Writing writing = boardService.findOne(writingId);
        writing.setViewCount(writing.getViewCount()+1);
        boardService.modify(writing);
        model.addAttribute("writing", writing);
        return "board/viewWriting";
    }

    public Writing createWriting(WritingForm form) {
        Writing writing = new Writing();
        writing.setContent(form.getContent());
        writing.setHeader(form.getHeader());
        writing.setCategory(form.getCategory());

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일 a h시 m분");
        String nowString = now.format(dateTimeFormatter);
        writing.setCreatedTime(nowString);
        writing.setLastModifiedTime(nowString);
        writing.setMember(memberService.find(1L));

        return writing;
    }
}
