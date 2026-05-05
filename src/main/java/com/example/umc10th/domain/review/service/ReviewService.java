package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.mission.entity.Restaurant;
import com.example.umc10th.domain.mission.repository.RestaurantRepository;
import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.domain.user.entity.Users;
import com.example.umc10th.domain.user.exception.UserException;
import com.example.umc10th.domain.user.exception.code.UserErrorCode;
import com.example.umc10th.domain.user.repository.UserRepository;
import com.example.umc10th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc10th.global.apiPayload.exception.ProjectException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;


    public ReviewResDTO.CreateReview createReview(Long userId, Long restaurantId, ReviewReqDTO.CreateReview dto) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ProjectException(GeneralErrorCode.NOT_FOUND));

        Review review = ReviewConverter.fromCreateReview(dto, user, restaurant);
        Review savedReview = reviewRepository.save(review);

        return new ReviewResDTO.CreateReview(savedReview.getId());
    }
}
