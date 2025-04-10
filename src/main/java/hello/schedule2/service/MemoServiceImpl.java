package hello.schedule2.service;

import hello.schedule2.entity.Member;
import hello.schedule2.entity.Memo;
import hello.schedule2.dto.memoDto.MemoResponseDto;
import hello.schedule2.dto.memoDto.MemoWithEmailResponseDto;
import hello.schedule2.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemoServiceImpl implements MemoService {

    private final MemoRepository memoRepository;

    @Override //완료
    public MemoResponseDto save(String userName, String title, String contents) {

        Memo memo = new Memo(userName, title, contents);
        Memo savedMemo = memoRepository.save(memo);

        return new MemoResponseDto(savedMemo.getId(),savedMemo.getTitle(), savedMemo.getContent());
    }

    @Override  //완료
    public MemoWithEmailResponseDto findById(Long id) {

        //Optional 타입으로 반환된다. 객체 안에 실제로 값이 있는지 확인하는 역할을 한다.
        /*
        isPresent()
        값이 있을 때 true를 반환하고, 값이 없을 때 false를 반환합니다.
        즉, Optional 객체 안에 값이 "존재하는지" 확인하는 메서드입니다.
        isEmpty()
        값이 없을 때 true를 반환하고, 값이 있을 때 false를 반환합니다.
        즉, Optional 객체 안에 값이 "비어 있는지" 확인하는 메서드입니다.
        * */
        Optional<Memo> optionalMemo = memoRepository.findById(id);

        if (optionalMemo.isEmpty()) { //만약 객체가 비어있다면?
            // 예외처리로 반환하자
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"id가 존재하지 않습니다.");
        }
        //Memo 타입으로 다시 반환
        Memo findMemo = optionalMemo.get();

        //member 타입으로 하나더 -->>member에 있는 email를 불러오기 위해 가독성을위해 따로준비
        Member writer = findMemo.getMember();

        MemoWithEmailResponseDto memoWithEmailResponseDto = new MemoWithEmailResponseDto(findMemo.getTitle(), findMemo.getContent(), writer.getEmail());
        return memoWithEmailResponseDto;
    }

    @Override
    public void delete(Long id) {

        //일단 게시물이 있는지 확인부터
        Optional<Memo> findId = memoRepository.findById(id);
        if (findId.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "삭제할수 있는 아이뒤가 없습니다." + id);
        }
        memoRepository.delete(findId.get());
    }

    @Override
    public List<MemoResponseDto> findAll() {

        //데이터 베이스에서 Memo 엔티티 목록을 가져옴 가져오니깐 리스트로 반환됬음
        List<Memo> findAllMemo = memoRepository.findAll();

        //반환할 Dto 리스트를 만들어줌
        List<MemoResponseDto> memoResponseDtoList = new ArrayList<>();

        //이제 옮겨줄일만 남았는데 향상된 for문을 사용함
        for (Memo memo : findAllMemo) {
            // 줄여서 할수있지만
//            memoResponseDtoList.add(new MemoResponseDto(memo.getId(),memo.getTitle(),memo.getContent()));

            MemoResponseDto dto = new MemoResponseDto(memo.getId(),memo.getTitle(),memo.getContent());
            memoResponseDtoList.add(dto); // 가독성을 위해서 나눠났음
        }
        return memoResponseDtoList;
    }
}
