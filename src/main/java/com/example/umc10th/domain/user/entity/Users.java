package com.example.umc10th.domain.user.entity;


import com.example.umc10th.domain.mission.entity.Location;
import com.example.umc10th.domain.mission.entity.mapping.UserMission;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.user.entity.mapping.UserFood;
import com.example.umc10th.domain.user.entity.mapping.UserTerm;
import com.example.umc10th.domain.user.enums.Gender;
import com.example.umc10th.domain.user.enums.SocialType;
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
@Table(name = "users")
public class Users extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Gender gender = Gender.NONE;

    @Column(name = "birth", nullable = false)
    private LocalDateTime birth;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "point", nullable = false)
    @Builder.Default
    private Integer point = 0;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "phone_verification", nullable = false)
    @Builder.Default
    private Boolean phoneVerification = false;

    @Enumerated(EnumType.STRING)
    @Column(name = "social_provider", nullable = false)
    private SocialType socialProvider;

    @Column(name = "social_uid")
    private String socialUid;

    @Column(name = "profile_url", columnDefinition = "TEXT")
    private String profileUrl;


    //양방향 관계 설정

    @OneToMany(mappedBy = "user")
    private List<Support> supportList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<UserTerm> userTermList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<UserFood> userFoodList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<UserMission> userMissionList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Review> reviewList = new ArrayList<>();

    @OneToOne(mappedBy = "user")
    private NotificationAllowList notificationAllowList;

}
