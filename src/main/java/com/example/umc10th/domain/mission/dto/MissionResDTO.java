package com.example.umc10th.domain.mission.dto;

import com.example.umc10th.domain.mission.entity.Mission;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class MissionResDTO {
    @Builder
    public record GetHome(
            Integer point,
            Integer clearedMissionCount,
            List<HomeMissionInfo> missions
    ) {}

    @Builder
    public record HomeMissionInfo(
            Long missionId,
            String missionContents,
            LocalDate deadline,
            Integer missionPoint,
            String restaurantName
    ) {}
}
