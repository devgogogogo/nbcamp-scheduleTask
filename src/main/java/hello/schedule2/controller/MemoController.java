package hello.schedule2.controller;

import hello.schedule2.dto.memo.MemoRequestDto;
import hello.schedule2.dto.memo.MemoResponseDto;
import hello.schedule2.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/memos")
@RequiredArgsConstructor
public class MemoController {
    private final MemoService memoService;

    @PostMapping // 메모생성
    public ResponseEntity<MemoResponseDto> createMemo(@RequestBody MemoRequestDto dto) {

        //서비스단으로 넘긴다.
        MemoResponseDto createMemo = memoService.createMemo(dto.getTitle(), dto.getContent());

        //반환값은
        return new ResponseEntity<>(createMemo, HttpStatus.CREATED);
    }

    @GetMapping("/{id}") //메모 단일조회
    public ResponseEntity<MemoResponseDto> memoFindById(@PathVariable long id) {

        //서비스 단으로 넘김
        MemoResponseDto memoResponseDto = memoService.memoFindById(id);

        //responseDto로 반환한다
        return new ResponseEntity<>(memoResponseDto, HttpStatus.OK);
    }

    @GetMapping //메모 전체조회
    public ResponseEntity<List<MemoResponseDto>> memoFindAll() {

        //서비스단으로 넘긴다.
        List<MemoResponseDto> memoResponseDtoList = memoService.memoFindAll();

        //리스트로 memoResponseDto로 반환한다.
        return new ResponseEntity<>(memoResponseDtoList, HttpStatus.OK);
    }

    @PatchMapping("/{id}") //단일 회원 메모 수정
    public ResponseEntity<MemoResponseDto> updateMemo(@PathVariable long id, @RequestBody MemoRequestDto dto) {

        MemoResponseDto memoResponseDto = memoService.updateMemo(id, dto.getTitle(), dto.getContent());

        return new ResponseEntity<>(memoResponseDto, HttpStatus.OK);
    }


    @DeleteMapping("/{id}") //회원 삭제
    public ResponseEntity<Void> deleteMemo(@PathVariable long id) {

        //서비스 단으로 넘긴다
        memoService.deleteMemo(id);

        //삭제이기 때문에 반환값이 없어도 된다 -->>void
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
