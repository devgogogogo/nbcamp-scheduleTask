package hello.schedule2.service;

import hello.schedule2.memoDto.MemoResponseDto;

public interface MemoService {

    MemoResponseDto save(String userName, String title, String contents);

    MemoResponseDto findById(Long id);

}
