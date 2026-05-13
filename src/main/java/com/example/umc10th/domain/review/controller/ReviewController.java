package com.example.umc10th.domain.review.controller;


import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "리뷰 작성 API", description = "식당에 대한 리뷰 작성")
    @PostMapping("/v1/restaurants/{restaurantId}/reviews")
    public ApiResponse<ReviewResDTO.CreateReview> createReview(
            @PathVariable Long restaurantId,
            @RequestBody @Valid ReviewReqDTO.CreateReview dto,
            @RequestParam Long userId
    ){
        BaseSuccessCode code = ReviewSuccessCode.REVIEW_CREATED;
        return ApiResponse.onSuccess(code, reviewService.createReview(userId, restaurantId, dto));

    }

    //GET 메서드가 더 적합하지만 id를 body로 받아야 하기 때문에 안전하게 POST 사용
    @Operation(summary = "사용자가 생성한 리뷰 조회 API", description = "내가 작성한 리뷰 조회하는 API")
    @PostMapping("/v1/users/me/reviews")
    public ApiResponse<ReviewResDTO.CursorPagination<ReviewResDTO.GetReview>> getReivews(
            @RequestBody Long userId,
            @RequestParam Integer pageSize,
            @RequestParam String cursor,
            @RequestParam String query
    ){
        BaseSuccessCode code = ReviewSuccessCode.OK;
        return ApiResponse.onSuccess(code, reviewService.getReview(userId, pageSize, cursor, query));

    }

}
