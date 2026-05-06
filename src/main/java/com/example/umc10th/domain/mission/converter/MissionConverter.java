package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.UserMission;

import java.util.ArrayList;
import java.util.List;

public class MissionConverter {

    public static MissionResDTO.MissionInfo toMissionInfo(UserMission userMission){
        return MissionResDTO.MissionInfo.builder()
                .userMissionId(userMission.getId())
                .missionContents(userMission.getMission().getMissionContents())
                .restaurantName(userMission.getMission().getRestaurant().getName())
                .missionPoint(userMission.getMission().getMissionPoint())
                .isCleared(userMission.isCleared())
                .build();
    }

    public static MissionResDTO.ViewMissions toViewMissions(List<UserMission> userMissionList){

        List<MissionResDTO.MissionInfo> missionInfoList = new ArrayList<>();

        for(UserMission userMission : userMissionList) {
            missionInfoList.add(MissionConverter.toMissionInfo(userMission));
        }

        return new MissionResDTO.ViewMissions(missionInfoList);
    }

}
