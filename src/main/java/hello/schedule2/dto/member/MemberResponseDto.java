package hello.schedule2.dto.member;

import lombok.Getter;

@Getter
public class MemberResponseDto {

   private final String username;
   private final String email;
   private final String mbti;


    public MemberResponseDto(String username, String email, String mbti) {
        this.username = username;
        this.email = email;
        this.mbti = mbti;
    }
}
