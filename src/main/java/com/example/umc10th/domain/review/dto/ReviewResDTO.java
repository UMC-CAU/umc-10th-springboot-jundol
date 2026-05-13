package com.example.umc10th.domain.review.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {

    public record CreateReview(
            Long reviewId
    ) {}

    //내가 작성한 리뷰 조회
    public record GetReviews(
            String userName,
            Long reviewId,
            Integer rating,
            Long restaurantId,
            String restaurantName,
            String reviewContent,
            LocalDateTime createdAt,
            List<ReplyInfo> replies
    ){}

    //리뷰에 대한 사장님의 응답
    public record ReplyInfo(
            String replyContent,
            LocalDateTime createdAt
    )


    //커서 기반 페이지네이션 틀
    @Builder
    public record CursorPagination<T>(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ){}
}