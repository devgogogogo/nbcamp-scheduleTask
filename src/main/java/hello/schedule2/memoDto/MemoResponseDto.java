package hello.schedule2.memoDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemoResponseDto {

    private final Long id;
    private final String title;
    private final String content;

}
