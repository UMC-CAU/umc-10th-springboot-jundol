package com.example.umc10th.domain.mission.entity;

import com.example.umc10th.domain.mission.entity.mapping.UserMission;
import com.example.umc10th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "mission")
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "mission_point", nullable = false)
    private Long missionPoint;

    @Column(name = "mission_contents", nullable = false, columnDefinition = "text")
    private String missionContents;

    @Column(name = "deadline", nullable = false)
    private LocalDateTime deadline;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @OneToMany(mappedBy = "user_mission")
    private List<UserMission> userMissionList = new ArrayList<>();
}
