package hello.schedule2.service;

import hello.schedule2.dto.memberDto.MemberResponseDto;
import hello.schedule2.dto.memberDto.SignUpResponseDto;

public interface MemberService {
    SignUpResponseDto signUp(String username, String password, String email);

    MemberResponseDto findById(Long id);
}
