package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.Mission;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query("SELECT m FROM Mission m " +
            "JOIN FETCH m.restaurant r " +
            "WHERE r.location.id = :locationId " +
            "AND NOT EXISTS (SELECT um FROM UserMission um WHERE um.user.id = :userId AND um.mission.id = m.id) " +
            "AND (:lastDeadline IS NULL OR m.deadline > :lastDeadline OR (m.deadline = :lastDeadline AND m.id > :lastMissionId)) " +
            "ORDER BY m.deadline ASC, m.id ASC")
    List<Mission> findPossibleMissions(
            @Param("userId") Long userId,
            @Param("locationId") Long locationId,
            @Param("lastDeadline") LocalDateTime lastDeadline,
            @Param("lastMissionId") Long lastMissionId,
            Pageable pageable
    );

    List<Mission> findAllByRestaurant_Id(Long restaurantId);
}
