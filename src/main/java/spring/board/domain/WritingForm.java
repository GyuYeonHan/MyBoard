package spring.board.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class WritingForm {

    private String header;
    private String content;
    private Category category;
}
