package spring.board.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    private Long id;

    private String username;
    private String password;

    @Builder
    public Member(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    protected Member() {

    }
}
