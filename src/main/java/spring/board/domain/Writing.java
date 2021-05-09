package spring.board.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Writing {

    @Id @GeneratedValue
    private Long id;

    private String header;
    private String content;
    private int viewCount = 0;
    private String createdTime;
    private String lastModifiedTime;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

}
