package hello.schedule2.controller;

import hello.schedule2.dto.memoDto.MemoRequestDto;
import hello.schedule2.dto.memoDto.MemoResponseDto;
import hello.schedule2.dto.memoDto.MemoWithEmailResponseDto;
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

    @GetMapping
    public ResponseEntity<List<MemoResponseDto>> findAll() {

        List<MemoResponseDto> memoResponseDto = memoService.findAll();

        return new ResponseEntity<>(memoResponseDto,HttpStatus.OK);
    }

    @GetMapping("/{id}") //단일 조회
    public ResponseEntity<MemoWithEmailResponseDto> findById(@PathVariable Long id) {

        MemoWithEmailResponseDto byId = memoService.findById(id);

        return new ResponseEntity<>(byId, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        memoService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
