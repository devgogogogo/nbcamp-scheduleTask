package hello.schedule2.dto;

import lombok.Getter;

@Getter
public class MemoResponseDto {

    private final String title;
    private final String content;

    public MemoResponseDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
