package spring.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import spring.board.Service.BoardService;
import spring.board.Service.MemberService;
import spring.board.domain.Writing;

import javax.validation.Valid;
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
    public String create(@Valid WritingForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "board/writingCreateForm";
        }

        Writing writing = createWriting(form);
        boardService.write(writing);

        return "redirect:/board";
    }

    @GetMapping("/{writingId}/modify")
    public String modifyForm(@PathVariable Long writingId, Model model) {
        Writing writing = boardService.findOne(writingId);
        model.addAttribute("writingForm", new WritingForm());
        model.addAttribute("writing", writing);
        return "board/writingModifyForm";
    }

    @PostMapping("/{writingId}/modify")
    public String modify(@PathVariable Long writingId, @Valid WritingForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "board/writingModifyForm";
        }

        Writing writing = boardService.findOne(writingId);
        writing.setHeader(form.getHeader());
        writing.setContent(form.getContent());
        writing.setCategory(form.getCategory());
        writing.setLastModifiedTime(getNowLocalDateTimeFormat(LocalDateTime.now()));
        boardService.modify(writing);
        log.info("here");

        return "redirect:/board/{writingId}";
    }

    @GetMapping("/{writingId}")
    public String viewWriting(@PathVariable Long writingId, Model model) {
        Writing writing = boardService.findOne(writingId);
        writing.setViewCount(writing.getViewCount()+1);
        boardService.modify(writing);
        model.addAttribute("writing", writing);
        return "board/viewWriting";
    }

    @GetMapping("/{writingId}/delete")
    public String delete(@PathVariable Long writingId) {
        Writing writing = boardService.findOne(writingId);
        boardService.delete(writing);
        return "redirect:/board";
    }

    public Writing createWriting(WritingForm form) {
        Writing writing = new Writing();
        writing.setContent(form.getContent());
        writing.setHeader(form.getHeader());
        writing.setCategory(form.getCategory());
        String nowString = getNowLocalDateTimeFormat(LocalDateTime.now());
        writing.setCreatedTime(nowString);
        writing.setLastModifiedTime(nowString);
        writing.setMember(memberService.find(1L));

        return writing;
    }

    private String getNowLocalDateTimeFormat(LocalDateTime localDateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일 a h시 m분");
        return localDateTime.format(dateTimeFormatter);
    }
}
