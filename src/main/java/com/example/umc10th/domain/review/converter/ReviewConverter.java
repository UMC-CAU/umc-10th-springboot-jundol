package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.mission.entity.Restaurant;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.entity.ReviewReply;
import com.example.umc10th.domain.user.entity.Users;

import java.util.List;

public class ReviewConverter {
    public static Review fromCreateReview(ReviewReqDTO.CreateReview dto, Users user, Restaurant restaurant){
        return Review.builder()
                .content(dto.content())
                .rating(dto.rating())
                .user(user)
                .restaurant(restaurant)
                .build();

    }

    //페이지네이션 틀 생성
    public static <T> ReviewResDTO.CursorPagination<T> toCursorPagination(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ){
        return ReviewResDTO.CursorPagination.<T>builder()
                .data(data)
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .pageSize(pageSize)
                .build();
    }

    //리뷰 조회
    public static ReviewResDTO.GetReview toGetReview(
            Review review
    ){
        return ReviewResDTO.GetReview.builder()
                .userName(review.getUser().getName())
                .reviewId(review.getId())
                .rating(review.getRating())
                .reviewContent(review.getContent())
                .restaurantId(review.getRestaurant().getId())
                .restaurantName(review.getRestaurant().getName())
                .createdAt(review.getCreatedAt())
                .replies(review.getReviewReplyList().stream().map(ReviewConverter::toReplyInfo).toList())
                .build();
    }

    //리뷰 조회의 응답
    public static ReviewResDTO.ReplyInfo toReplyInfo(
            ReviewReply reply
    ){
        return ReviewResDTO.ReplyInfo.builder()
                .replyId(reply.getId())
                .replyContent(reply.getContent())
                .createdAt(reply.getCreatedAt())
                .build();
    }


}
