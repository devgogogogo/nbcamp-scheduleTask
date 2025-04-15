package hello.schedule2.dto.member;

import lombok.Getter;

@Getter
public class MemberResponseDto {

    private final long id;
    private final String username;
    private final String email;
    private final String mbti;


    public MemberResponseDto(long id, String username, String email, String mbti) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.mbti = mbti;
    }
}
