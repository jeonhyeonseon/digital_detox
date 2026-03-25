package com.digitaldetox.digital_detox.challenge.domain;

import com.digitaldetox.digital_detox.member.entity.Member;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;

    private LocalDate startDate;

    private int currentDay; // 현재 몇 일차인지

    @Enumerated(EnumType.STRING)
    private MemberChallengeStatus memberChallengeStatus;

    private int streak; // 연속 수행 일수

    public void updateStreak(int streak) {
        this.streak = streak;
    }
}
