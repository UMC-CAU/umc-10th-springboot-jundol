package com.example.umc10th.domain.mission.dto;

import com.example.umc10th.domain.mission.entity.Mission;
import lombok.Builder;
import java.time.LocalDateTime;
import java.util.List;

public class MissionResDTO {

    // 홈 화면 조회 응답
    @Builder
    public record GetHome(
            Integer point,
            Integer clearedMissionCount,
            List<HomeMissionInfo> missions
    ) {}


    //홈 화면 조회 미션 정보들
    @Builder
    public record HomeMissionInfo(
            Long missionId,
            String missionContents,
            LocalDateTime deadline,
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
            Long userMissionId,
            Integer missionPoint,
            String restaurantName,
            String missionContents,
            boolean isCleared
    ) {}

    //미션 성공 누르기
    @Builder
    public record MissionComplete(
            Long userMissionId,
            Integer missionPoint, //성공한 미션의 point점수
            Integer totalPoint // 미션 성공 이후 늘어난 총 point
    ){}

    //가게 내 미션 조회
    @Builder
    public record GetMission(
            Long missionId,
            Integer point,
            String conditional
    ){}

    //오프셋 기반 페이지네이션 틀
    @Builder
    public record OffsetPagination<T>(
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    ){}

    //커서 기반 페이지네이션 틀
    @Builder
    public record CursorPagination<T>(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ){}

}
