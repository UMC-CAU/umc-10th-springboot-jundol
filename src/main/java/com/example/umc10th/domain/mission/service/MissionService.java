package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.Restaurant;
import com.example.umc10th.domain.mission.entity.mapping.UserMission;
import com.example.umc10th.domain.mission.exception.RestaurantException;
import com.example.umc10th.domain.mission.exception.code.RestaurantErrorCode;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.mission.repository.RestaurantRepository;
import com.example.umc10th.domain.mission.repository.UserMissionRepository;
import com.example.umc10th.domain.user.entity.Users;
import com.example.umc10th.domain.user.exception.UserException;
import com.example.umc10th.domain.user.exception.code.UserErrorCode;
import com.example.umc10th.domain.user.repository.UserRepository;
import com.example.umc10th.global.apiPayload.exception.ProjectException;
import org.springframework.transaction.annotation.Transactional;
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
    private final RestaurantRepository restaurantRepository;

    public MissionResDTO.GetHome getHome(MissionReqDTO.Home dto) {
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


    // 가게 미션 생성
    @Transactional
    public Void createMission(Long restaurantId, MissionReqDTO.CreateMission dto) {
        //가게 찾기
        Restaurant restaurant = restaurantRepository.findById(restaurantId).
                orElseThrow(()-> new RestaurantException(RestaurantErrorCode.NOT_FOUND));

        //미션 생성
        Mission mission = MissionConverter.toRestaurantMission(restaurant, dto);

        missionRepository.save(mission);
        return null;
    }

    public List<MissionResDTO.GetMission> getMissions(Long restautantId) {
        List<Mission> missionList = missionRepository.findAllByRestaurant_Id(restautantId);

        return missionList.stream()
                .map(MissionConverter::toGetMission)
                .toList();
    }
}
