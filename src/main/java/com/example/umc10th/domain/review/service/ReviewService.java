package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.mission.entity.Restaurant;
import com.example.umc10th.domain.mission.repository.RestaurantRepository;
import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.exception.ReviewException;
import com.example.umc10th.domain.review.exception.code.ReviewErrorCode;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import com.example.umc10th.domain.user.entity.Users;
import com.example.umc10th.domain.user.exception.UserException;
import com.example.umc10th.domain.user.exception.code.UserErrorCode;
import com.example.umc10th.domain.user.repository.UserRepository;
import com.example.umc10th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc10th.global.apiPayload.exception.ProjectException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;


    @Transactional
    public ReviewResDTO.CreateReview createReview(Long userId, Long restaurantId, ReviewReqDTO.CreateReview dto) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ProjectException(GeneralErrorCode.NOT_FOUND));

        Review review = ReviewConverter.fromCreateReview(dto, user, restaurant);
        Review savedReview = reviewRepository.save(review);

        return new ReviewResDTO.CreateReview(savedReview.getId());
    }

    //리뷰 조회 메서드
    @Transactional(readOnly = true)
    public ReviewResDTO.CursorPagination<ReviewResDTO.GetReview> getReview(
            Long userId,
            Integer pageSize,
            String cursor,
            String query
    ) {
       //페이지 정보들을 PageRequest로 만들기
       PageRequest pageRequest = PageRequest.of(0, pageSize);

       long idCursor;
       Slice<Review> reviewSlice;
       String nextCursor;

       //커서가 있는 경우
       if(!cursor.equals("-1")){

           String[] cursorSplit = cursor.split(":");
           switch(query.toLowerCase()) {
               case "id":
                   //커서 타입 반환
                   String prevCursor = cursorSplit[0];
                   idCursor = Long.parseLong(cursorSplit[1]);

                   reviewSlice = reviewRepository.findReviewsByUser_idAndIdLessThanOrderByIdDesc(
                           userId,
                           idCursor,
                           pageRequest
                   );
                   break;
               default:
                   throw new ReviewException(ReviewErrorCode.QUERY_NOT_VALID);
           }
       }
       else
       {
           //커서 없이 조회
           reviewSlice = reviewRepository.findReviewsByUser_idOrderByIdDesc(userId, pageRequest);
       }

       //다음 커서 계산
        if (reviewSlice.getContent().isEmpty()) {
            nextCursor = "-1";
        } else {
            nextCursor = "id" + ":" + reviewSlice.getContent().getLast().getId();
        }

        return ReviewConverter.toCursorPagination(
                reviewSlice.map(ReviewConverter::toGetReview).toList(),
                reviewSlice.hasNext(),
                nextCursor,
                reviewSlice.getSize()

        );
    }
}
