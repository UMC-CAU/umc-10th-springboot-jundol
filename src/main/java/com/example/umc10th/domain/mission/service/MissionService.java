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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
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

    // 미션 보기 (진행 중 | 진행 완료)
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

    //가게 미션 조회 service 메서드
    public MissionResDTO.Pagination<MissionResDTO.GetMission> getMissions(
            Long restaurantId,
            Integer pageSize,
            Integer pageNumber,
            String sort
    ) {

        Sort sortInfo;
        if (sort != null){
            sortInfo = Sort.by(sort);
        }
        else{
            sortInfo = Sort.by("id").descending();
        }

        //페이지 정보들을 PageRequest로 만들기
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortInfo);

        //가게 내 미션들 조회
        Page<Mission> missionList = missionRepository.findAllByRestaurant_Id(restaurantId, pageRequest);

        //미션들 응답 DTO로 포장하기
        return MissionConverter.toPagination(
                missionList.map(MissionConverter::toGetMission).toList(),
                missionList.getNumber(),
                missionList.getSize()
        );
    }
}
