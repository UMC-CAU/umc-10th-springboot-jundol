package com.example.umc10th.domain.mission.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {
    OK(HttpStatus.OK, "MEMBER200_1", "성공적으로 미션을 조회하였습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;

}
