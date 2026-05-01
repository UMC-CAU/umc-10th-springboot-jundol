package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    public static ReviewResDTO.CreateReview createReview(Long userId, Long restaurantId, ReviewReqDTO.CreateReview dto) {
        return new ReviewResDTO.CreateReview(1L);
    }
}
