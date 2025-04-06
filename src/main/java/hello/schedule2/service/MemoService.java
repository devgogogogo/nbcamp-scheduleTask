package hello.schedule2.service;

import hello.schedule2.dto.MemoResponseDto;

public interface MemoService {

    MemoResponseDto save(String userName, String title, String contents);
}
