package hello.schedule2.dto.memoDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
public class MemoWithEmailResponseDto {
    private final String title;
    private final String contents;
    private final String email;
}
