package com.example.umc10th.domain.review.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {
    REVIEW_CREATED(HttpStatus.OK, "REVIEW200_1", "성공적으로 리뷰를 작성하였습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
