package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MissionService {

    //private final MissionRepository missionRepository;

    public MissionResDTO.GetHome getHome(MissionReqDTO.Home dto) {


        List<MissionResDTO.HomeMissionInfo> missions = List.of(
                MissionResDTO.HomeMissionInfo.builder()
                        .missionId(1L)
                        .missionContents("미션 내용 예시 1")
                        .deadline(LocalDate.of(2026, 6, 1))
                        .missionPoint(100)
                        .restaurantName("식당 예시 1")
                        .build(),
                MissionResDTO.HomeMissionInfo.builder()
                        .missionId(2L)
                        .missionContents("미션 내용 예시 2")
                        .deadline(LocalDate.of(2026, 7, 1))
                        .missionPoint(200)
                        .restaurantName("식당 예시 2")
                        .build()
        );

        return MissionResDTO.GetHome.builder()
                .point(1500)
                .clearedMissionCount(3)
                .missions(missions)
                .build();
    }

    public MissionResDTO.ViewMissions viewMissions(MissionReqDTO.ViewMissions dto) {
        List<MissionResDTO.MissionInfo> missions = List.of(
                MissionResDTO.MissionInfo.builder()
                        .missionId(1L)
                        .missionPoint(100)
                        .restaurantName("식당 예시 1")
                        .missionContents("미션 내용 예시 1")
                        .build(),
                MissionResDTO.MissionInfo.builder()
                        .missionId(2L)
                        .missionPoint(200)
                        .restaurantName("식당 예시 2")
                        .missionContents("미션 내용 예시 2")
                        .build()
        );

        return new MissionResDTO.ViewMissions(missions);
    }

    public MissionResDTO.MissionComplete missionComplete(MissionReqDTO.MissionComplete dto) {
        return MissionResDTO.MissionComplete.builder()
                .userMissionId(1L)
                .missionPoint(100)
                .totalPoint(1600)
                .build();
    }
}
