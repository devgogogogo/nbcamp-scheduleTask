package hello.schedule2.controller;

import hello.schedule2.dto.member.MemberRequestDto;
import hello.schedule2.dto.member.MemberResponseDto;
import hello.schedule2.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup") //회원가입  //todo 이게 회원가입인가 ?
    public ResponseEntity<MemberResponseDto> signup(@RequestBody MemberRequestDto dto) {

        // service 단으로 dto와 함께 전달
        MemberResponseDto signupResponseDto = memberService.signup(dto.getUsername(), dto.getEmail(), dto.getPassword(), dto.getMbti());

        //반환값은 responseDto
        return new ResponseEntity<>(signupResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}") //회원 단일 조회
    public ResponseEntity<MemberResponseDto> findById(@PathVariable Long id) {

        //서비스단으로 전달
        MemberResponseDto findById = memberService.findById(id);

        //리턴은 responseDto
        return new ResponseEntity<>(findById, HttpStatus.OK);
    }

    @GetMapping //회원 전체조회
    public ResponseEntity<List<MemberResponseDto>> findAll() {

        //서비스단으로 넘겨준다.
        List<MemberResponseDto> findAll = memberService.findAll();


        return new ResponseEntity<>(findAll, HttpStatus.OK);
    }

    @DeleteMapping("/{id}") //회원 삭제
    //삭제이기 떄문에 반환같이 없어도 된다. -->>Void
    public ResponseEntity<Void> delete(@PathVariable Long id,@RequestBody MemberRequestDto dto) {

        //서비스 단으로 넘긴다.
        memberService.delete(id,dto.getPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    //todo 로그인
//    @PostMapping()
//
//    //todo  로그아웃
//    @PostMapping()

}
