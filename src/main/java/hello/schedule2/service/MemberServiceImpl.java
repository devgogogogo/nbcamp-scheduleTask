package hello.schedule2.service;

import hello.schedule2.entity.Member;
import hello.schedule2.memberDto.SignUpResponseDto;
import hello.schedule2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public SignUpResponseDto signUp(String username, String password, String email) {

        Member member = new Member(username, password, email); //entity객체에 dto로 왔던 정보를 넣어주고

        Member savedMember = memberRepository.save(member); // 저장된 entity객체를  DB에 넣어주고

        // 넣어준 DB정보를 SignUpResponseDto 객체에 정보를 넣어준다.
        return new SignUpResponseDto(savedMember.getId(), savedMember.getUserName(), savedMember.getEmail());
    }
}
