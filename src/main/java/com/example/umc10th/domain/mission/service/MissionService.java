package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.UserMission;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.mission.repository.UserMissionRepository;
import com.example.umc10th.domain.user.entity.Users;
import com.example.umc10th.domain.user.exception.UserException;
import com.example.umc10th.domain.user.exception.code.UserErrorCode;
import com.example.umc10th.domain.user.repository.UserRepository;
import com.example.umc10th.global.apiPayload.exception.ProjectException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final UserMissionRepository userMissionRepository;
    private final MissionRepository missionRepository;
    private final UserRepository userRepository;

    public MissionResDTO.GetHome getHome(MissionReqDTO.Home dto) {
        /*
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
                */

        Integer clearedMissionCount = userMissionRepository.countCompleteMissions(dto.userId(), dto.locationId());

        Users user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));
        Integer point = user.getPoint();

        List<Mission> possibleMissions = missionRepository.findPossibleMissions(
                dto.userId(),
                dto.locationId(),
                dto.lastDeadline(),
                dto.lastMissionId(),
                PageRequest.of(0, 5)
        );

        return MissionConverter.toGetHome(point, clearedMissionCount, possibleMissions);

    }

    public MissionResDTO.ViewMissions viewMissions(MissionReqDTO.ViewMissions dto) {

        List<UserMission> userMissionList = userMissionRepository.findUserMission(
                dto.userId(),
                dto.isCompleted(),
                dto.lastUserMissionId(),
                PageRequest.of(0,5)
        );

        return MissionConverter.toViewMissions(userMissionList);
    }

    public MissionResDTO.MissionComplete missionComplete(MissionReqDTO.MissionComplete dto) {
        return MissionResDTO.MissionComplete.builder()
                .userMissionId(1L)
                .missionPoint(100)
                .totalPoint(1600)
                .build();
    }
}
