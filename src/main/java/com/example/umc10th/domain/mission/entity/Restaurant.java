package com.example.umc10th.domain.mission.entity;

import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "owner_number", nullable = false)
    private Long ownerNumber;

    @Column(name = "detail_location", nullable = false)
    private String detailLocation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @OneToMany(mappedBy = "restaurant")
    private List<Mission> missionList = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant")
    private List<Review> reviewList = new ArrayList<>();
}
