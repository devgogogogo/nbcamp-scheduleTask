package hello.schedule2.controller;

import hello.schedule2.dto.member.LoginRequestDto;
import hello.schedule2.dto.member.MemberRequestDto;
import hello.schedule2.dto.member.MemberResponseDto;
import hello.schedule2.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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

    @PostMapping("/signup") //회원가입
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
    public ResponseEntity<Void> delete(@PathVariable Long id, @RequestBody MemberRequestDto dto) {

        //서비스 단으로 넘긴다.
        memberService.delete(id, dto.getPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // todo 성공 한번 더 공부하려고 todo로 남김
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto dto, HttpServletRequest request) {

        try {
            //서비스 단에 이메일,password를 넘긴다.
            MemberResponseDto userLogin = memberService.loginUser(dto.getEmail(), dto.getPassword());

            //세션생성을 한다(true로) -> 있으면 반환하고, 없으면 새로 생성한다. 중요 !
            // false는 없으면 null으로 반환하기 때문에 안한다.
            HttpSession session = request.getSession(true);

            session.setAttribute("sessionKey", userLogin);

            //30분 (초 단위이다.)
            session.setMaxInactiveInterval(1800);

            return ResponseEntity.ok("로그인 성공");

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }


    //todo 로그아웃 한번 더 공부하려고 todo로 남김
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        //session이 존재하면 --> 로그인 했다.
        if (session != null) {
            session.invalidate();// 세션을 만료시킨다.
        }

        return ResponseEntity.ok("로그아웃 성공");
    }

}
