package com.example.umc10th.domain.mission.entity;


import com.example.umc10th.domain.mission.enums.Address;
import com.example.umc10th.domain.user.entity.Users;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name", nullable = false)
    @Enumerated(EnumType.STRING)
    private Address name;

    //연관관계
    @OneToMany(mappedBy = "location")
    private List<Restaurant> restaurantList = new ArrayList<>();

    @OneToMany(mappedBy = "location")
    private List<Users> usersList = new ArrayList<>();
}
