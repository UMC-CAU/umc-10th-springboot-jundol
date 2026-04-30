package com.example.umc10th.domain.user.dto;

import lombok.Builder;

public class UserResDTO {
    @Builder
    public record GetInfo( //마이페이지용
            String name,
            String profileUrl,
            String email,
            String phoneNumber,
            Integer point
    ){}

}
