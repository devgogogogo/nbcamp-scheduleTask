package hello.schedule2.service;

import hello.schedule2.memoDto.MemoResponseDto;
import hello.schedule2.memoDto.MemoWithEmailResponseDto;

import java.util.List;

public interface MemoService {

    MemoResponseDto save(String userName, String title, String contents);

    MemoWithEmailResponseDto findById(Long id);

    List<MemoWithEmailResponseDto> findAll();
}
