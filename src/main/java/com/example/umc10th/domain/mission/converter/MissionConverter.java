package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.Restaurant;
import com.example.umc10th.domain.mission.entity.mapping.UserMission;

import java.util.ArrayList;
import java.util.List;

public class MissionConverter {

    //홈 화면 조회에 사용되는 하나의 도전 가능한 미션
    public static MissionResDTO.HomeMissionInfo toHomeMissionInfo(Mission mission){
        return MissionResDTO.HomeMissionInfo.builder()
                .missionId(mission.getId())
                .missionContents(mission.getMissionContents())
                .deadline(mission.getDeadline())
                .missionPoint(mission.getMissionPoint())
                .restaurantName(mission.getRestaurant().getName())
                .build();
    }

    //홈 화면 조회
    public static MissionResDTO.GetHome toGetHome(Integer point, Integer clearedMissionCount, List<Mission> possibleMissions){
        List<MissionResDTO.HomeMissionInfo> missions = new ArrayList<>();

        for(Mission mission : possibleMissions){
            missions.add(toHomeMissionInfo(mission));
        }

        return MissionResDTO.GetHome.builder()
                .point(point)
                .clearedMissionCount(clearedMissionCount)
                .missions(missions)
                .build();
    }


    // 미션 조회 (진행 중 | 진행 완료) 에 사용되는 하나의 미션 정보
    public static MissionResDTO.MissionInfo toMissionInfo(UserMission userMission){
        return MissionResDTO.MissionInfo.builder()
                .userMissionId(userMission.getId())
                .missionContents(userMission.getMission().getMissionContents())
                .restaurantName(userMission.getMission().getRestaurant().getName())
                .missionPoint(userMission.getMission().getMissionPoint())
                .isCleared(userMission.isCleared())
                .build();
    }

    // 미션 조회 (진행 중 | 진행 완료)
    public static MissionResDTO.ViewMissions toViewMissions(List<UserMission> userMissionList){

        List<MissionResDTO.MissionInfo> missionInfoList = new ArrayList<>();

        for(UserMission userMission : userMissionList) {
            missionInfoList.add(MissionConverter.toMissionInfo(userMission));
        }

        return new MissionResDTO.ViewMissions(missionInfoList);
    }

    //가게 미션 생성
    public static Mission toRestaurantMission(
            Restaurant restaurant,
            MissionReqDTO.CreateMission dto
    ){
        return Mission.builder()
                .restaurant(restaurant)
                .missionContents(dto.conditional())
                .missionPoint(dto.point())
                .deadline(dto.deadline())
                .build();
    }

    //가게 내 미션 조회
    public static MissionResDTO.GetMission toGetMission(Mission mission){
        return MissionResDTO.GetMission.builder()
                .conditional(mission.getMissionContents())
                .point(mission.getMissionPoint())
                .missionId(mission.getId())
                .build();
    }

    //페이지네이션 틀 생성
    public static <T> MissionResDTO.CursorPagination<T> toPagination(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ){
        return MissionResDTO.CursorPagination.<T>builder()
                .data(data)
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .pageSize(pageSize)
                .build();
    }

}
