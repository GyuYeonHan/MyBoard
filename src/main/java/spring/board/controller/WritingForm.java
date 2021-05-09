package spring.board.controller;

import lombok.Getter;
import lombok.Setter;
import spring.board.domain.Category;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class WritingForm {

    @NotEmpty(message = "제목을 입력해주세요.")
    private String header;

    @NotEmpty(message = "내용을 입력해주세요.")
    private String content;
    private Category category;
}
