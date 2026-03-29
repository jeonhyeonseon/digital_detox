package com.digitaldetox.digital_detox.badge.repository;

import com.digitaldetox.digital_detox.badge.domain.Badge;
import com.digitaldetox.digital_detox.badge.domain.MemberBadge;
import com.digitaldetox.digital_detox.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberBadgeRepository extends JpaRepository<MemberBadge, Long> {

    boolean existsByMemberAndBadge(Member member, Badge badge);

}
