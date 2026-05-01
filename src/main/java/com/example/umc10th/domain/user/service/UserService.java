package com.example.umc10th.domain.user.service;

import com.example.umc10th.domain.user.dto.UserReqDTO;
import com.example.umc10th.domain.user.dto.UserResDTO;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public UserResDTO.GetInfo getInfo(UserReqDTO.GetInfo dto) {
        return UserResDTO.GetInfo.builder()
                .name("유저 예시 1")
                .profileUrl("https://example.com/profile/1.png")
                .email("test@example.com")
                .phoneNumber("010-1234-5678")
                .point(1500)
                .build();
    }

    //회원 가입 메서드
    public UserResDTO.SignUp signUp(UserReqDTO.SignUp dto) {
        return new UserResDTO.SignUp(1L);
    }
}
