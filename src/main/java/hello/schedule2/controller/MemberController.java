package hello.schedule2.controller;

import hello.schedule2.memberDto.SignUpRequestDto;
import hello.schedule2.memberDto.SignUpResponseDto;
import hello.schedule2.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto dto) {

        SignUpResponseDto signUpResponseDto = memberService.signUp(dto.getUsername(), dto.getPassword(), dto.getEmail());

        return new ResponseEntity<>(signUpResponseDto,HttpStatus.CREATED);
    }
}
