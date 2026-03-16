package com.digitaldetox.digital_detox.focus.repository;

import com.digitaldetox.digital_detox.focus.domain.FocusSession;
import com.digitaldetox.digital_detox.focus.domain.SessionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface FocusSessionRepository extends JpaRepository<FocusSession, Long> {

    List<FocusSession> findByMemberIdAndSessionStatusAndStartedAtBetween(Long memberId, SessionStatus sessionStatus, LocalDateTime startOfDay, LocalDateTime endOfDay);

}
