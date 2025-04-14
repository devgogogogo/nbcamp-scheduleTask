package hello.schedule2.dto.member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class MemberRequestDto {

    @NotBlank
    private final String username;

    @NotBlank
    @Email
    private final String email;

    @NotBlank
    private final String password;

    private final String mbti;

    public MemberRequestDto(String username, String email, String password, String mbti) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.mbti = mbti;
    }
}
