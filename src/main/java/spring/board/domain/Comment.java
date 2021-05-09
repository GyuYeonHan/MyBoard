package spring.board.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
public class Comment extends BaseTimeEntity {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String content;

    @Builder
    public Comment(String createdTime, String modifiedTime, Long id, Post post, Member member, String content) {
        super(createdTime, modifiedTime);
        this.id = id;
        this.post = post;
        this.member = member;
        this.content = content;
    }

    protected Comment() {
        super();
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
