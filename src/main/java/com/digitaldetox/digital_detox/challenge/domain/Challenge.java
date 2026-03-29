package com.digitaldetox.digital_detox.challenge.domain;

import com.digitaldetox.digital_detox.badge.domain.Badge;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_challenge")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long challengeId;

    private String title;

    private String description;

    // 7일, 14일, 30일
    @Column(nullable = false)
    private int durationDays;

    @Column(nullable = false)
    private int difficulty;

    @Enumerated(EnumType.STRING)
    private ChallengeTheme challengeTheme;

    private String expectedEffect;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "badge_id", nullable = false)
    private Badge badge;

}
