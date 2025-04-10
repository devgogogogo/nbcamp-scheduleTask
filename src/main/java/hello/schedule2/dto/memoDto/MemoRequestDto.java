package hello.schedule2.dto.memoDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemoRequestDto {

    private final String username;
    private final String title;
    private final String contents;

}
