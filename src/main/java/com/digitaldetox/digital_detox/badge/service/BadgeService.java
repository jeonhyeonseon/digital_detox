package com.digitaldetox.digital_detox.badge.service;

import com.digitaldetox.digital_detox.badge.domain.Badge;
import com.digitaldetox.digital_detox.badge.domain.MemberBadge;
import com.digitaldetox.digital_detox.badge.repository.BadgeRepository;
import com.digitaldetox.digital_detox.badge.repository.MemberBadgeRepository;
import com.digitaldetox.digital_detox.member.entity.Member;
import com.digitaldetox.digital_detox.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class BadgeService {

    private final BadgeRepository badgeRepository;
    private final MemberBadgeRepository memberBadgeRepository;

    public void awardBadge(Member member, Badge badge) {

        boolean alreadyHasBadge = memberBadgeRepository.existsByMemberAndBadge(member, badge);

        if (alreadyHasBadge) {
            return;
        }

        MemberBadge memberBadge = MemberBadge.builder()
                .member(member)
                .badge(badge)
                .achievedAt(LocalDateTime.now())
                .build();

        memberBadgeRepository.save(memberBadge);
    }
}
