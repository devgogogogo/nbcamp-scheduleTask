package hello.schedule2.controller;

import hello.schedule2.dto.MemoRequestDto;
import hello.schedule2.dto.MemoResponseDto;
import hello.schedule2.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    @PostMapping("/sign")
    public ResponseEntity<MemoResponseDto> save(@RequestBody MemoRequestDto dto) {

        MemoResponseDto saved = memoService.save(dto.getUserName(), dto.getTitle(), dto.getContents());

        return new ResponseEntity<>(saved,HttpStatus.CREATED);
    }



}
