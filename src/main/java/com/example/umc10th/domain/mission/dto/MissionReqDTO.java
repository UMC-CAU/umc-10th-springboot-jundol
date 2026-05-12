package com.example.umc10th.domain.mission.dto;

import java.time.LocalDateTime;

public class MissionReqDTO {

    //가게 미션 생성
    public record CreateMission(
            LocalDateTime deadline,
            Integer point,
            String conditional

    ) {}

    //홈화면 조회 요청
    public record Home(
            long locationId,
            LocalDateTime lastDeadline,
            Long lastMissionId,
            Long userId
    ) {}

    // 미션 목록 조회(진행 중/ 진행 완료) 요청
    public record ViewMissions(
            boolean isCompleted,
            Long lastUserMissionId,
            Long userId
    ) {}

    // 미션 성공 dto
    public record MissionComplete(
            Long userMissionId,
            Long userId
    ) {}
}
