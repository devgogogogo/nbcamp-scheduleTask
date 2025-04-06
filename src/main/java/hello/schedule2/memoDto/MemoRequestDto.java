package hello.schedule2.memoDto;

import lombok.Getter;

@Getter
public class MemoRequestDto {

    private final String username;
    private final String title;
    private final String contents;

    public MemoRequestDto(String userName, String title, String contents) {
        this.username = userName;
        this.title = title;
        this.contents = contents;
    }
}
