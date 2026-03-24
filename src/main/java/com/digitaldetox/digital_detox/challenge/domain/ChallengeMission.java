package com.digitaldetox.digital_detox.challenge.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name = "tbl_challenge_mission",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"challenge_id", "day_number"})
        }
)
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChallengeMission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long challengeMissionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id", nullable = false)
    private Challenge challenge;

    @Column(name = "day_number", nullable = false)
    private int dayNumber;

    private String missionContent;
}
