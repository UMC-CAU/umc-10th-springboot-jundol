package com.example.umc10th.global.apiPayload.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum GeneralErrorCode implements BaseErrorCode{ //@Getter가 있으므로 interface의 함수를 직접 구현하지 않아도 에러x

    //requiredArgsConstructor를 통해 필요한 매개변수를 받는 생성자 자동 생성
    //enum 생성 시 매개변수로 Httpstatus, code, message를 매개변수로 하는 생성자 호출
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400_1", "잘못된 요청입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401_1", "인증되지 않았습니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403_1", "접근이 금지되었습니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "COMMON404_1", "해당 리소스를 찾을 수 없습니다."),
    ;
    //httpStatus.BAD_REQUEST.. 등은 스프링의 HttpStatus에 정의되어 있는 http 상태 정보
    //에러 코드 enum에서 하는 것 -> 특정 http 상태(에러)에서 사용할 에러 코드와 메시지를 지정

    private final HttpStatus status;
    private final String code;
    private final String message;
}
