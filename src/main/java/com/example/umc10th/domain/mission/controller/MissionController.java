package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {

    private final MissionService missionService;

    @GetMapping("v1/home") // 홈화면 조회 controller
    public ApiResponse<MissionResDTO.GetHome> getHome(
            @RequestParam Long locationId,
            @RequestParam(required = false) LocalDate lastDeadLine,
            @RequestParam(required = false) Long lastMissionId
            //필수가 아닌 쿼리 파라미터(페이징 용)은 required 를 false로 설정
            ){

        MissionReqDTO.home dto = new MissionReqDTO.home(
                locationId,
                lastDeadLine,
                lastMissionId
        );

        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.getHome(dto));
    }
}
