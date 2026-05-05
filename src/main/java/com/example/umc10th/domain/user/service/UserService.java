package com.example.umc10th.domain.user.service;

import com.example.umc10th.domain.user.controller.UserController;
import com.example.umc10th.domain.user.converter.UserConverter;
import com.example.umc10th.domain.user.dto.UserReqDTO;
import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.domain.user.entity.Users;
import com.example.umc10th.domain.user.exception.UserException;
import com.example.umc10th.domain.user.exception.code.UserErrorCode;
import com.example.umc10th.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //마이페이지
    public UserResDTO.GetInfo getInfo(UserReqDTO.GetInfo dto) {

        //dto에서 유저 id 추출
        Long userId = dto.id();
        // db에서 해당 유저 ID로 데이터 조회
        Users user = userRepository.findById(userId)
                .orElseThrow(()-> new UserException(UserErrorCode.USER_NOT_FOUND));

        // 컨버터를 이용해 응답 DTO 생성 & return
        return UserConverter.toGetInfo(user);
    }

    //회원 가입 메서드
    public UserResDTO.SignUp signUp(UserReqDTO.SignUp dto) {
        return new UserResDTO.SignUp(1L);
    }
}
