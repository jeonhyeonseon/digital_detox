package com.digitaldetox.digital_detox.focus.repository;

import com.digitaldetox.digital_detox.focus.domain.FocusSession;
import com.digitaldetox.digital_detox.focus.domain.SessionStatus;
import com.digitaldetox.digital_detox.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface FocusSessionRepository extends JpaRepository<FocusSession, Long> {

    List<FocusSession> findByMemberAndSessionStatusAndStartedAtBetween(Member member, SessionStatus sessionStatus, LocalDateTime startOfDay, LocalDateTime endOfDay);

    @Query("""
            select coalesce(sum(f.actualTime), 0)
            from FocusSession f
            where f.member.memberId = :memberId
            and f.sessionStatus = com.digitaldetox.digital_detox.focus.domain.SessionStatus.COMPLETED
            and f.startedAt >= :startOfDay
            and f.startedAt < :endOfDay
            """)
    int sumTodayFocusTime(Long memberId, LocalDateTime startOfDay, LocalDateTime endOfDay);

    @Query("""
            select f
            from FocusSession f
            where f.member.memberId = :memberId
            and f.sessionStatus = com.digitaldetox.digital_detox.focus.domain.SessionStatus.COMPLETED
            and f.startedAt >= :startDateTime
            and f.startedAt < :endDateTime
            order by f.startedAt asc
            """)
    List<FocusSession> findWeeklyFocusSessions(Long memberId, LocalDateTime startDateTime, LocalDateTime endDateTime);
}
