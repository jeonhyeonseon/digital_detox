package com.digitaldetox.digital_detox.badge.domain;

import com.digitaldetox.digital_detox.member.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_badge")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Badge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long badgeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "member_id", nullable = false)
    private Member member;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BadgeType badgeType;

    @Column(nullable = false)
    private LocalDateTime achievedDate;
}
