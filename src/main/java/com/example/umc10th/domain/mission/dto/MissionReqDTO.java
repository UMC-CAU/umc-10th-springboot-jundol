package com.example.umc10th.domain.mission.dto;

import java.time.LocalDate;

public class MissionReqDTO {

    public record home( //홈화면 조회 요청
            long locationId,
            LocalDate lastDeadline,
            Long lastMissionId
    ) {}
}
