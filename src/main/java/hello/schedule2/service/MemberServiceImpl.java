package hello.schedule2.service;

import hello.schedule2.entity.Member;
import hello.schedule2.memberDto.MemberResponseDto;
import hello.schedule2.memberDto.SignUpResponseDto;
import hello.schedule2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

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

    @Override
    public MemberResponseDto findById(Long id) {

        //jpa로 findById()를 사용해서 Optional 타입으로 멤버를 끄집어 내고 객체에 실제로 값이 있는지 확인한다.
        Optional<Member> findById = memberRepository.findById(id);

        if (!findById.isPresent()) {  // 이번에는 .isEmpty()가 아니라 .isPresent()를 사용해봤다. 다양하게 이용해보려고
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "현재 존재하지 않은 회원입니다." + id);
        }
        //끄집에 낸 member객체를
        Member findMember = findById.get();

        //responseDto 한테 정보를 준다.

        return new MemberResponseDto(findMember.getUserName(), findMember.getEmail());
    }
}
