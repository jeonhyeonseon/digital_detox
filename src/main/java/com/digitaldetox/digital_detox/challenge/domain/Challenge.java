package com.digitaldetox.digital_detox.challenge.domain;

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
    private int durationDays;

    private int difficulty;

    @Enumerated(EnumType.STRING)
    private ChallengeTheme challengeTheme;

    private String expectedEffect;

    private String badge;
}
