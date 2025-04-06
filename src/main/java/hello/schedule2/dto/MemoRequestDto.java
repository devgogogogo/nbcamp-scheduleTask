package hello.schedule2.dto;

import lombok.Getter;

@Getter
public class MemoRequestDto {

    private final String userName;
    private final String title;
    private final String contents;

    public MemoRequestDto(String userName, String title, String contents) {
        this.userName = userName;
        this.title = title;
        this.contents = contents;
    }
}
