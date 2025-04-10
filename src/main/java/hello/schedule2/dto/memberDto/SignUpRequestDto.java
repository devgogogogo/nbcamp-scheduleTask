package hello.schedule2.dto.memberDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignUpRequestDto {

    private final String username;
    private final String password;
    private final String email;

}
