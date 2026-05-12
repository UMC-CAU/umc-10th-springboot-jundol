package com.example.umc10th.domain.mission.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {
    CREATED(HttpStatus.OK, "MISSION200_1", "성공적을 미션을 생성하였습니다."),
    OK(HttpStatus.OK, "MISSION200_2", "성공적으로 미션을 조회하였습니다."),
    MISSION_COMPLETE_OK(HttpStatus.OK, "MISSION200_3", "미션 달성 처리되었습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;

}
