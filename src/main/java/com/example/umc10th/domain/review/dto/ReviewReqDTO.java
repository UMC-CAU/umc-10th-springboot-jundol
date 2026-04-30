package com.example.umc10th.domain.review.dto;

import lombok.Builder;

import java.util.List;

public class ReviewReqDTO {
    @Builder
    public record CreateReview(
            Long Id, //임시 설정
            String content,
            Double rating,
            List<String> photoUrl
    ) {}
}
