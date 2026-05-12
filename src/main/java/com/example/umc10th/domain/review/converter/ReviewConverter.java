package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.mission.entity.Restaurant;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.user.entity.Users;

public class ReviewConverter {
    public static Review fromCreateReview(ReviewReqDTO.CreateReview dto, Users user, Restaurant restaurant){
        return Review.builder()
                .content(dto.content())
                .rating(dto.rating())
                .user(user)
                .restaurant(restaurant)
                .build();

    }

}
