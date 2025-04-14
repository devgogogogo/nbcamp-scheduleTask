package hello.schedule2.commonResponse;

import org.springframework.http.HttpStatus;

//todo 아직안한거.. 어떻게 해야될지 잘 모르겟습니다 ..ㅠㅠ
public enum CommonResponse {
    SIGNUP_SUCCESS(HttpStatus.CREATED, "회원이 완료되었습니다."),
    CREATE_MEMO_SUCCESS(HttpStatus.CREATED, "메모가 생성되었습니다."),
    LOGIN_SUCCESS(HttpStatus.OK, "로그인이 완료되었습니다.");

    private final HttpStatus httpStatus;
    private final String message;

    CommonResponse(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
    }
