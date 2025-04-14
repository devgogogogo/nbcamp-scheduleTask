package hello.schedule2.service;

import hello.schedule2.dto.member.MemberResponseDto;
import hello.schedule2.entity.Member;
import hello.schedule2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;


    @Override //회원가입
    public MemberResponseDto signup(String username, String email, String password, String mbti) {

        //객체에 데이터를 집어넣고
        Member member = new Member(username, email, password, mbti);

        //repository에 객체를 집어넣고
        Member savedMember = memberRepository.save(member);

        //집어넣은 repository를 dto에 전달
        return new MemberResponseDto(savedMember.getUsername(), savedMember.getEmail(), savedMember.getMbti());
    }

    @Override //회원 단일조회
    public MemberResponseDto findById(Long id) {

        //findById를 불러오면 npe을 안전하게 처리하기 위해 optional 타입으로 불러봐 진다.
//        Optional<Member> findById = memberRepository.findById(id);
//        //만약 아이뒤가 없다면?
//        if (!findById.isPresent()) {
//            //예외처리 하자
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"찾는 아이디가 존재하지 않습니다.");
//        }
        Member member = memberRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        //있다면?? -->> 맴버객체를 불러와서


        //responseDto에 넣어준다.
        return new MemberResponseDto(member.getUsername(), member.getEmail(), member.getMbti());
    }

    @Override //회원 전체조회
    public List<MemberResponseDto> findAll() {

        //향상된 문법?
        List<Member> findAll = memberRepository.findAll();

        List<MemberResponseDto> list = findAll.stream()
                .map(m -> new MemberResponseDto(m.getUsername(), m.getEmail(), m.getMbti()))
                .toList();
        return list;

        //원초적인 방법
//        //repository에서 findAll을 불러오면 List타입으로 불러와진다.
//        List<Member> findAll = memberRepository.findAll();

//        //담아갈 List를 만들어서
//        List<MemberResponseDto> memberResponseDtoList = new ArrayList<>();
//
//        //for문을 돌려
//        for (Member member : findAll) {
//            // DB안에 있는 member객체를 responseDto
//            MemberResponseDto find = new MemberResponseDto(member.getUsername(), member.getEmail(), member.getMbti());
//
//            memberResponseDtoList.add(find);
//        }
//        return ;
    }

    @Override //회원 삭제
    public void delete(Long id,String password) {

        //findById 로 회원을 찾은다음
        Member member = memberRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "삭제할 아이다가 없습니다."));

        //todo 비밀번호 같은지 확인
        boolean isSamePassword = member.getPassword().equals(password);
        if (isSamePassword) {
            memberRepository.delete(member);
        }
    }
}
