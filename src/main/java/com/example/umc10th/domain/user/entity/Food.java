package com.example.umc10th.domain.user.entity;


import com.example.umc10th.domain.user.entity.mapping.UserFood;
import com.example.umc10th.domain.user.enums.FoodList;
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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "food")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "food_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private FoodList foodType;

    @OneToMany(mappedBy = "food")
    private List<UserFood> userFoodList = new ArrayList<>();
}
