package spring.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import spring.board.Service.BoardService;
import spring.board.Service.CommentService;
import spring.board.Service.MemberService;
import spring.board.domain.Comment;
import spring.board.domain.Post;

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
    private final CommentService commentService;

    @GetMapping
    public String boardMain(Model model) {
        List<Post> posts = boardService.findAllPosts();
        model.addAttribute("posts", posts);
        return "board/board";
    }

    @GetMapping("/create")
    public String createPostForm(Model model) {
        model.addAttribute("postForm", new PostForm());
        return "board/postCreateForm";
    }

    @PostMapping("/create")
    public String writePost(@Valid PostForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "board/postCreateForm";
        }

        Post post = createPost(form);
        boardService.write(post);

        return "redirect:/board";
    }

    @GetMapping("/{postId}/modify")
    public String modifyPostForm(@PathVariable Long postId, Model model) {
        Post post = boardService.findOne(postId);
        model.addAttribute("postForm", new PostForm());
        model.addAttribute("post", post);
        return "board/postModifyForm";
    }

    @PostMapping("/{postId}/modify")
    public String modifyPost(@PathVariable Long postId, @Valid PostForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "board/postModifyForm";
        }

        Post post = boardService.findOne(postId);

        Post postParam = Post.builder()
                .id(post.getId())
                .content(form.getContent())
                .title(form.getTitle())
                .category(form.getCategory())
                .createdTime(post.getCreatedTime())
                .modifiedTime(getNowLocalDateTimeFormat(LocalDateTime.now()))
                .member(post.getMember())
                .viewCount(post.getViewCount())
                .build();

        boardService.modify(postParam);

        return "redirect:/board/{postId}";
    }

    @GetMapping("/{postId}")
    public String viewPost(@PathVariable Long postId, Model model) {
        Post post = boardService.findOne(postId);
        post.increaseViewCount();
        boardService.modify(post);
        model.addAttribute("post", post);

        model.addAttribute("comments", post.getComments());
        model.addAttribute("commentForm", new CommentForm());
        return "board/viewPost";
    }

    @PostMapping("/{postId}")
    public String createComment(@PathVariable Long postId, @Valid CommentForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/board/{postId}";
        }

        Post post = boardService.findOne(postId);
        Comment comment = createComment(post, form);
        commentService.write(comment);

        return "redirect:/board/{postId}";
    }

    @GetMapping("/{postId}/delete")
    public String deletePost(@PathVariable Long postId) {
        Post post = boardService.findOne(postId);
        boardService.delete(post);

        return "redirect:/board";
    }

    @GetMapping("/{postId}/comment/{commentId}/delete")
    public String deleteComment(@PathVariable Long postId, @PathVariable Long commentId) {
        Comment comment = commentService.findOne(commentId);
        commentService.delete(comment);
        return "redirect:/board/{postId}";
    }

    public Post createPost(PostForm form) {
        String nowString = getNowLocalDateTimeFormat(LocalDateTime.now());

        Post post = Post.builder()
                .content(form.getContent())
                .title(form.getTitle())
                .category(form.getCategory())
                .createdTime(nowString)
                .modifiedTime(nowString)
                .member(memberService.find(1L))
                .build();

        return post;
    }

    public Comment createComment(Post post, CommentForm form) {
        String nowString = getNowLocalDateTimeFormat(LocalDateTime.now());
        Comment comment = Comment.builder()
                .content(form.getContent())
                .createdTime(nowString)
                .modifiedTime(nowString)
                .member(memberService.find(1L))
                .build();
        post.addComment(comment);

        return comment;
    }

    private String getNowLocalDateTimeFormat(LocalDateTime localDateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일 a h시 m분");
        return localDateTime.format(dateTimeFormatter);
    }
}
