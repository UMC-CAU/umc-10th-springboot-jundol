package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
            @RequestParam(required = false) Long lastMissionId,
            @RequestParam Long userId
            //필수가 아닌 쿼리 파라미터(페이징 용)은 required 를 false로 설정
            ){

        MissionReqDTO.Home dto = new MissionReqDTO.Home(
                locationId,
                lastDeadLine,
                lastMissionId,
                userId
        );

        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.getHome(dto));
    }

    @GetMapping("/v1/users/me/missions")
    public ApiResponse<MissionResDTO.ViewMissions> viewMissions(
            @RequestParam boolean isCompleted,
            @RequestParam(required = false) Long lastUserMissionId,
            @RequestParam Long userId
    ){
        MissionReqDTO.ViewMissions dto = new MissionReqDTO.ViewMissions(
                isCompleted,
                lastUserMissionId,
                userId
        );

        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.viewMissions(dto));
    }

    @PostMapping("/v1/users/me/missions/{UserMissionId}/success-request")
    public ApiResponse<MissionResDTO.MissionComplete> missionComplete(
            @PathVariable("UserMissionId") Long userMissionId,
            @RequestParam Long userId
    ){
        MissionReqDTO.MissionComplete dto = new MissionReqDTO.MissionComplete(
                userMissionId,
                userId
        );

        BaseSuccessCode code = MissionSuccessCode.MISSION_COMPLETE_OK;
        return ApiResponse.onSuccess(code, missionService.missionComplete(dto));
    }


}
