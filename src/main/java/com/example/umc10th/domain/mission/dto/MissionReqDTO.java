package com.example.umc10th.domain.mission.dto;

import java.time.LocalDate;

public class MissionReqDTO {

    //홈화면 조회 요청
    public record Home(
            long locationId,
            LocalDate lastDeadline,
            Long lastMissionId
    ) {}

    // 미션 목록 조회(진행 중/ 진행 완료) 요청
    public record ViewMissions(
            boolean isCompleted,
            Long lastUserMissionId
    ) {}

    // 미션 성공 dto
    public record MissionComplete(
            Long userMissionId
    ) {}
}
