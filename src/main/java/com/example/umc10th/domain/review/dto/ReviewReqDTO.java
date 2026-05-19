package com.example.umc10th.domain.review.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;

import java.util.List;

public class ReviewReqDTO {
    @Builder
    public record CreateReview(
            @NotBlank(message = "리뷰 내용은 빈칸일 수 없습니다.")
            String content,
            @NotNull(message = "리뷰 평점은 필수입니다.")
            @DecimalMin(value = "0.0", message = "평점은 0.0 이상이어야 합니다.")
            @DecimalMax(value = "5.0", message = "평점은 5.0 이하여야 합니다.")
            Double rating,
            List<String> photoUrl
    ) {}
}
