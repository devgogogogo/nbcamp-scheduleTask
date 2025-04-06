package hello.schedule2.controller;

import hello.schedule2.memoDto.MemoRequestDto;
import hello.schedule2.memoDto.MemoResponseDto;
import hello.schedule2.memoDto.MemoWithEmailResponseDto;
import hello.schedule2.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    @PostMapping
    public ResponseEntity<MemoResponseDto> save(@RequestBody MemoRequestDto dto) {

        //dto에 유저이름, 제목, 내용을 넣어서 서비스에 넘겨준다.
        MemoResponseDto saved = memoService.save(dto.getUsername(), dto.getTitle(), dto.getContents());

        //반환값은 응답dto로 반환한다.
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/{id}") //단일 조회
    public ResponseEntity<MemoWithEmailResponseDto> findById(@PathVariable Long id) {

        memoService.findById(id);


        return new ResponseEntity<>(,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<MemoWithEmailResponseDto>> findAll() {

        List<MemoWithEmailResponseDto> memoList = memoService.findAll();

        return new ResponseEntity<>(memoList,HttpStatus.OK);
    }
}
