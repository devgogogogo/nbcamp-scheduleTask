package hello.schedule2.dto.memo;

import hello.schedule2.commonResponse.CommonResponse;
import lombok.Getter;

@Getter
public class MemoResponseDto {

    // 보통은 id 까지 넣어준다 !
    private final Long id;
    private final CommonResponse commonResponse;
    private final String title;
    private final String content;


    public MemoResponseDto(Long id, CommonResponse commonResponse, String title, String content) {
        this.id = id;
        this.commonResponse = commonResponse;
        this.title = title;
        this.content = content;
    }
}
