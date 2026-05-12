package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.mapping.UserMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    //미션 조회 (진행 중| 진행 완료)
    @Query("SELECT um FROM UserMission um " +
    "JOIN FETCH um.mission m JOIN FETCH m.restaurant r " +
    "WHERE um.user.id = :userId AND um.isCleared = :isCleared AND (:lastUserMissionId IS NULL OR um.id < :lastUserMissionId) ORDER BY um.id DESC")
    List<UserMission> findUserMission(
            @Param("userId") Long userId,
            @Param("isCleared") boolean isCleared,
            @Param("lastUserMissionId") Long lastUserMissionId,
            Pageable pageable
            );


    //완료한 미션 수 세기(홈 화면에서 사용(미션 10개 달성시 ...))
    @Query("SELECT COUNT(um) FROM UserMission um " +
            "JOIN um.mission m JOIN m.restaurant r " +
            "WHERE um.user.id = :userId AND r.location.id = :locationId AND um.isCleared = true")
    Integer countCompleteMissions(
            @Param("userId") Long userId,
            @Param("locationId") Long locationId
    );


    Page<UserMission> findAllByUser_IdAndIsCleared(Long userId, boolean isCleared, PageRequest pageRequest);
}
