package com.example.umc10th.domain.user.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "notification_allow_list")
public class NotificationAllowList {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "is_allow_event", nullable = false)
    @Builder.Default
    private boolean isAllowEvent = false;

    @Column(name = "is_allow_review_response", nullable = false)
    @Builder.Default
    private boolean isAllowReviewResponse = false;

    @Column(name = "is_allow_support_response", nullable = false)
    @Builder.Default
    private boolean isAllowSupportResponse = false;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private Users users;

}
