package com.example.umc10th.domain.mission.dto;

import com.example.umc10th.domain.mission.entity.Mission;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class MissionResDTO {

    // 홈 화면 조회 응답
    @Builder
    public record GetHome(
            Integer point,
            Integer clearedMissionCount,
            List<HomeMissionInfo> missions
    ) {}


    //홈 화면 조회, 미션 조회할 때 응답으로 조회되는 미션들
    @Builder
    public record HomeMissionInfo(
            Long missionId,
            String missionContents,
            LocalDate deadline,
            Integer missionPoint,
            String restaurantName
    ) {}

    // 미션 목록 조회(진행 중/ 진행 완료)
    public record ViewMissions(
            List<MissionInfo> missions
    ) {}

    // 미션 목록 조회(진행 중/ 진행 완료)
    @Builder
    public record MissionInfo(
            Long missionId,
            Integer missionPoint,
            String restaurantName,
            String missionContents
    ) {}


}
