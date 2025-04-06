package hello.schedule2.service;

import hello.schedule2.memberDto.MemberResponseDto;
import hello.schedule2.memberDto.SignUpResponseDto;

public interface MemberService {
    SignUpResponseDto signUp(String username, String password, String email);

    MemberResponseDto findById(Long id);
}
