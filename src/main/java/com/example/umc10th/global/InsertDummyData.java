package com.example.umc10th.global;

import com.example.umc10th.domain.mission.entity.Location;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.Restaurant;
import com.example.umc10th.domain.mission.entity.mapping.UserMission;
import com.example.umc10th.domain.mission.enums.Address;
import com.example.umc10th.domain.mission.repository.LocationRepository;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.mission.repository.RestaurantRepository;
import com.example.umc10th.domain.mission.repository.UserMissionRepository;
import com.example.umc10th.domain.user.entity.Users;
import com.example.umc10th.domain.user.enums.SocialType;
import com.example.umc10th.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class InsertDummyData implements CommandLineRunner {

    private final LocationRepository locationRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;

    @Override
    @Transactional
    public void run(String... args) {
        if (userRepository.count() > 0) return;

        // Location
        Location seoul = locationRepository.save(Location.builder().name(Address.SEOUL).build());
        Location busan = locationRepository.save(Location.builder().name(Address.BUSAN).build());

        // Users
        Users user = userRepository.save(Users.builder()
                .name("예시 사용자")
                .email("example@email.com")
                .password("password1234")
                .phoneNumber("010-0000-0000")
                .birth(LocalDateTime.of(2000, 1, 1, 0, 0))
                .location(seoul)
                .address("예시 주소")
                .socialProvider(SocialType.KAKAO)
                .build());

        // Restaurant (서울 2곳, 부산 1곳)
        Restaurant r1 = restaurantRepository.save(Restaurant.builder().name("예시 식당 1").ownerNumber(1012345678L).detailLocation("예시 상세주소 1").location(seoul).build());
        Restaurant r2 = restaurantRepository.save(Restaurant.builder().name("예시 식당 2").ownerNumber(1087654321L).detailLocation("예시 상세주소 2").location(seoul).build());
        Restaurant r3 = restaurantRepository.save(Restaurant.builder().name("예시 식당 3").ownerNumber(1011112222L).detailLocation("예시 상세주소 3").location(busan).build());

        // Mission (서울 식당 5개, 부산 식당 2개)
        Mission m1 = missionRepository.save(Mission.builder().missionPoint(100).missionContents("미션 내용 예시 1").deadline(LocalDateTime.of(2026, 6, 1, 0, 0)).restaurant(r1).build());
        Mission m2 = missionRepository.save(Mission.builder().missionPoint(200).missionContents("미션 내용 예시 2").deadline(LocalDateTime.of(2026, 6, 15, 0, 0)).restaurant(r1).build());
        Mission m3 = missionRepository.save(Mission.builder().missionPoint(150).missionContents("미션 내용 예시 3").deadline(LocalDateTime.of(2026, 7, 1, 0, 0)).restaurant(r2).build());
        Mission m4 = missionRepository.save(Mission.builder().missionPoint(300).missionContents("미션 내용 예시 4").deadline(LocalDateTime.of(2026, 7, 15, 0, 0)).restaurant(r2).build());
        Mission m5 = missionRepository.save(Mission.builder().missionPoint(250).missionContents("미션 내용 예시 5").deadline(LocalDateTime.of(2026, 8, 1, 0, 0)).restaurant(r1).build());
        Mission m6 = missionRepository.save(Mission.builder().missionPoint(100).missionContents("미션 내용 예시 6").deadline(LocalDateTime.of(2026, 6, 1, 0, 0)).restaurant(r3).build());
        Mission m7 = missionRepository.save(Mission.builder().missionPoint(200).missionContents("미션 내용 예시 7").deadline(LocalDateTime.of(2026, 7, 1, 0, 0)).restaurant(r3).build());

        // UserMission (미션1,2 완료 / 미션3 진행중 / 나머지 도전안함)
        userMissionRepository.save(UserMission.builder().user(user).mission(m1).isCleared(true).build());
        userMissionRepository.save(UserMission.builder().user(user).mission(m2).isCleared(true).build());
        userMissionRepository.save(UserMission.builder().user(user).mission(m3).isCleared(false).build());
    }
}
