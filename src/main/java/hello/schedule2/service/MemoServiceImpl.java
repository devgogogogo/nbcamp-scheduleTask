package hello.schedule2.service;

import hello.schedule2.entity.Member;
import hello.schedule2.entity.Memo;
import hello.schedule2.memoDto.MemoResponseDto;
import hello.schedule2.memoDto.MemoWithEmailResponseDto;
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

    @Override
    public MemoResponseDto save(String userName, String title, String contents) {

        Memo memo = new Memo(userName, title, contents);
        Memo savedMemo = memoRepository.save(memo);

        return new MemoResponseDto(savedMemo.getId(),savedMemo.getTitle(), savedMemo.getContent());
    }

    @Override
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

        //member 타입으로 하나더 -->> email를 불러오기 위해 가독성을위해 따로준비
        Member writer = findMemo.getMember();

        MemoWithEmailResponseDto memoWithEmailResponseDto = new MemoWithEmailResponseDto(findMemo.getTitle(), findMemo.getContent(), writer.getEmail());
        return memoWithEmailResponseDto;
    }

    @Override
    public List<MemoWithEmailResponseDto> findAll() {

        List<Memo> findEntityList = memoRepository.findAll();

        List<MemoWithEmailResponseDto> memoWithEmailResponseDtoList = new ArrayList<>();

        for (Memo memo : findEntityList) {
            MemoWithEmailResponseDto memoWithEmailResponseDto = new MemoWithEmailResponseDto(me);

        }

        return ;
    }
}
