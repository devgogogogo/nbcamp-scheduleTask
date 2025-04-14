package hello.schedule2.service;

import hello.schedule2.dto.memo.MemoResponseDto;

import java.util.List;

public interface MemoService {
    MemoResponseDto createMemo(String title, String content);

    MemoResponseDto memoFindById(long id);

    List<MemoResponseDto> memoFindAll();

    void deleteMemo(long id);

    MemoResponseDto updateMemo(long id, String title, String content);
}
