package hello.schedule2.service;

import hello.schedule2.dto.MemoResponseDto;
import hello.schedule2.entity.Memo;
import hello.schedule2.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemoServiceImpl implements MemoService {

    private final MemoRepository memoRepository;

    @Override
    public MemoResponseDto save(String userName, String title, String contents) {

        Memo memo = new Memo(userName, title, contents);
        Memo savedMemo = memoRepository.save(memo);

        return new MemoResponseDto(savedMemo.getTitle(), savedMemo.getContent());
    }
}
