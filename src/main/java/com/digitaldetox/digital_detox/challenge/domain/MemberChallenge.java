package com.digitaldetox.digital_detox.challenge.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "tbl_member_challenge")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberChallenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberChallengeId;

    private Long memberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;

    private LocalDate startDate;

    @Enumerated(EnumType.STRING)
    private MemberChallengeStatus memberChallengeStatus;

    private int streak;

}
