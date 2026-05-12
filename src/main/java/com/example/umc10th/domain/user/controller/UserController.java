package com.example.umc10th.domain.user.controller;

import com.example.umc10th.domain.user.exception.code.UserSuccessCode;
import com.example.umc10th.domain.user.dto.UserReqDTO;
import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.domain.user.service.UserService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    @Operation(summary = "마이페이지", description = "마이페이지 정보 조회")
    @PostMapping("/v1/users/me")
    public ApiResponse<UserResDTO.GetInfo> getInfo(
            @RequestBody UserReqDTO.GetInfo dto
    ){
        BaseSuccessCode code = UserSuccessCode.FIND_OK;
        return ApiResponse.onSuccess(code, userService.getInfo(dto));
    }

    @Operation(summary = "회원가입", description = "회원가입을 위한 api")
    @PostMapping("/auth/v1/users/signup")
    public ApiResponse<UserResDTO.SignUp> signUp(
            @RequestBody UserReqDTO.SignUp dto
    ){
        BaseSuccessCode code = UserSuccessCode.SIGNUP_OK;
        return ApiResponse.onSuccess(code, userService.signUp(dto));
    }



}
