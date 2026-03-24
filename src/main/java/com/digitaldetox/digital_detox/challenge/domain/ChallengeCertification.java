package com.digitaldetox.digital_detox.challenge.domain;

import com.digitaldetox.digital_detox.diary.domain.Mood;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "tbl_challenge_certification",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"member_challenge_id", "day_number"})
        }
)
@Getter
@NoArgsConstructor
public class ChallengeCertification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long challengeCertificationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_challenge_id", nullable = false)
    private MemberChallenge memberChallenge;

    @Column(name = "day_number", nullable = false)
    private int dayNumber;

    private String reviewContent;

    @Enumerated(EnumType.STRING)
    private Mood mood;

    @Column(nullable = false)
    private LocalDateTime certificationDate;
}
