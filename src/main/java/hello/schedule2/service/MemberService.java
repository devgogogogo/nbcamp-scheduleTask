package hello.schedule2.service;

import hello.schedule2.memberDto.SignUpResponseDto;

public interface MemberService {
    SignUpResponseDto signUp(String username, String password, String email);
}
