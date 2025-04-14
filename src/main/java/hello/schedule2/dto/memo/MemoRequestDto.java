package hello.schedule2.dto.memo;

import lombok.Getter;

@Getter
public class MemoRequestDto {

    private final String title;
    private final String content;

    public MemoRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}