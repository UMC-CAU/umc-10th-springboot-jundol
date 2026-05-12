package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {

    private final MissionService missionService;

    @Operation(summary = "가게 미션 생성")
    @PostMapping("v1/restaurants/{restaurantId}/missions")
    public ApiResponse<Void> createMission(
            @PathVariable Long restaurantId,
            @RequestBody MissionReqDTO.CreateMission dto
    ){
        BaseSuccessCode code = MissionSuccessCode.CREATED;
        return ApiResponse.onSuccess(code, missionService.createMission(restaurantId, dto));
    }

    @Operation(summary = "가게 내 미션들 조회")
    @GetMapping("/v1/restaurantss/{restaurantId}/missions")
    public ApiResponse<MissionResDTO.Pagination<MissionResDTO.GetMission>> getMissions(
            @PathVariable Long restaurantId,
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNumber,
            @RequestParam(required = false) String sort
    ){
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.getMissions(restaurantId, pageSize, pageNumber, sort));
    }

    @Operation(summary = "홈 화면 조회", description = "홈 화면의 포인트, 성공한 미션 수, 도전 추천 미션을 조회")
    @GetMapping("v1/home") // 홈화면 조회 controller
    public ApiResponse<MissionResDTO.GetHome> getHome(
            @RequestParam Long locationId,
            @RequestParam(required = false) LocalDateTime lastDeadline,
            @RequestParam(required = false) Long lastMissionId,
            @RequestParam Long userId
            //필수가 아닌 쿼리 파라미터(페이징 용)은 required 를 false로 설정
            ){

        MissionReqDTO.Home dto = new MissionReqDTO.Home(
                locationId,
                lastDeadline,
                lastMissionId,
                userId
        );

        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.getHome(dto));
    }

    @Operation(summary = "미션 목록 조회", description = "진행 중, 혹은 진행 완료한 미션을 조회")
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
