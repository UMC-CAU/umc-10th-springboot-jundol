package com.example.umc10th.domain.user.converter;

import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.domain.user.entity.Users;

public class UserConverter {
    public static UserResDTO.GetInfo toGetInfo(Users user) {
        return UserResDTO.GetInfo.builder()
                .email(user.getEmail())
                .name(user.getName())
                .profileUrl(user.getProfileUrl())
                .phoneNumber(user.getPhoneNumber())
                .point(user.getPoint())
                .build();
    }
}
