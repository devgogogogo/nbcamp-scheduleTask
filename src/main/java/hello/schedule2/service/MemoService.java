package hello.schedule2.service;

import hello.schedule2.dto.memoDto.MemoResponseDto;
import hello.schedule2.dto.memoDto.MemoWithEmailResponseDto;

import java.util.List;

public interface MemoService {

    MemoResponseDto save(String userName, String title, String contents);

    MemoWithEmailResponseDto findById(Long id);


    void delete(Long id);

    List<MemoResponseDto> findAll();
}
