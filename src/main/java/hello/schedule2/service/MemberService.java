package hello.schedule2.service;

import hello.schedule2.dto.member.MemberResponseDto;

import java.util.List;

public interface MemberService {
    MemberResponseDto signup(String username, String email, String password, String mbti);

    MemberResponseDto findById(Long id);

    List<MemberResponseDto> findAll();

    void delete(Long id,String password);

}
