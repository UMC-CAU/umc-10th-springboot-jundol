package com.example.umc10th.domain.user.controller;

import com.example.umc10th.domain.user.code.UserSuccessCode;
import com.example.umc10th.domain.user.dto.UserReqDTO;
import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.domain.user.service.UserService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    @PostMapping("/v1/users/me")
    public ApiResponse<UserResDTO.GetInfo> getInfo(
            @RequestBody UserReqDTO.GetInfo dto
    ){
        BaseSuccessCode code = UserSuccessCode.OK;
        return ApiResponse.onSuccess(code, userService.getInfo(dto));
    }



}
