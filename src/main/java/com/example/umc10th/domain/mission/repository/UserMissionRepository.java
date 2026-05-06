package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.mapping.UserMission;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    @Query("SELECT um FROM UserMission um " +
    "JOIN FETCH um.mission m JOIN FETCH m.restaurant r " +
    "WHERE um.user.id = :userId AND um.isCleared = :isCleared AND (:lastUserMissionId IS NULL OR um.id < :lastUserMissionId) ORDER BY um.id DESC")
    List<UserMission> findUserMission(
            @Param("userId") Long userId,
            @Param("isCleared") boolean isCleared,
            @Param("lastUserMissionId") Long lastUserMissionId,
            Pageable pageable
            );

}
