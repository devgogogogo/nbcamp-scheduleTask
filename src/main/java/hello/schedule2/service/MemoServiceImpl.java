package hello.schedule2.service;

import hello.schedule2.commonResponse.CommonResponse;
import hello.schedule2.dto.memo.MemoResponseDto;
import hello.schedule2.entity.Memo;
import hello.schedule2.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemoServiceImpl implements MemoService {
    //생성자 주입
    private final MemoRepository memoRepository;

    @Override //메모생성
    public MemoResponseDto createMemo(String title, String content) {

        //객체에 메모를 넣어주고
        Memo createMemo = new Memo(title, content);

        //DB에 그 객체를 넣어준다
        Memo savedMemo = memoRepository.save(createMemo);

        //DB에서 다시 끄집어 내서 ResponseDto에 넣어준다.
        return new MemoResponseDto(savedMemo.getId(), CommonResponse.CREATE_MEMO_SUCCESS,savedMemo.getTitle(), savedMemo.getContent());
    }

    @Override // 메모 단일 조회
    public MemoResponseDto memoFindById(long id) {

        //repository에 findByud를 조회를 해서 끄집어 낸다.
        Optional<Memo> memoFindById = memoRepository.findById(id);

        //optional로 나오기 때문에 NPE을 처리해준다. 만약 id가 없다면?
        if (!memoFindById.isPresent()) {
            //없다면 -->>예외처리를 해준다.
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "조회할 메모가 없습니다.");
        }
        //memo 를 get 한다.
        Memo memo = memoFindById.get();

        //responseDto에 반환한다.
        return new MemoResponseDto(memo.getId(),CommonResponse.CREATE_MEMO_SUCCESS,memo.getTitle(), memo.getContent());
    }

    @Override // 메모 전체 조회
    public List<MemoResponseDto> memoFindAll() {

        //향상된 빙법?
        List<Memo> memoFindAll = memoRepository.findAll();
        List<MemoResponseDto> list = memoFindAll.stream()
                .map(m -> new MemoResponseDto(m.getId(),CommonResponse.CREATE_MEMO_SUCCESS,m.getTitle(), m.getContent()))
                .toList();

        return list;
        //쉬운방법
//        //DB에서 전체 메모를 불러온다.
//        List<Memo> findAll = memoRepository.findAll();
//
//        //반환할 List를 생성한다.
//        List<MemoResponseDto> memoResponseDtoList = new ArrayList<>();
//
//        //for문을 돌려서 List에 넣어준다.
//        for (MemoResponseDto memo : memoResponseDtoList) {
//            MemoResponseDto memoResponseDto = new MemoResponseDto(memo.getTitle(), memo.getContent());
//            memoResponseDtoList.add(memoResponseDto);
//        }
//        return memoResponseDtoList;
    }

    @Override //todo 메모 삭제 -->>본인인증이 들어가야함
    public void deleteMemo(long id) {

        //일단 삭제할 memo가 있는지 조회한다.
        Optional<Memo> findById = memoRepository.findById(id);

        //만약 조회한 메모가 없다면?
        if (!findById.isPresent()) {
            //예외 처리
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "삭제할 아이디가 없습니다.");
        }

        //있으면 삭제 ㄱㄱ
        memoRepository.deleteById(id);
    }

    @Override //메모 수정  -->> 질문하자 이렇게 하는게 맞는지
    public MemoResponseDto updateMemo(long id, String title, String content) {
        Optional<Memo> findById = memoRepository.findById(id);

        //수정할 아이디가 없으면 -->> 예외처리
        if (!findById.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "수정할 아이디가 없습니다.");
        }

        //찾은 id를 get
        Memo memo = findById.get();

        //타이틀 수정
        memo.setTitle(title);

        ////내용 수정
        memo.setContent(content);

        //수정한 객체를 DB에 저장한다.
        Memo updated = memoRepository.save(memo);

        //responseDto를 반환한다.
        return new MemoResponseDto(updated.getId(),CommonResponse.CREATE_MEMO_SUCCESS,updated.getTitle(), updated.getContent());
    }
}
