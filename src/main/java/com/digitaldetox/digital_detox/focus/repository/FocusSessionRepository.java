package com.digitaldetox.digital_detox.focus.repository;

import com.digitaldetox.digital_detox.focus.domain.FocusSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FocusSessionRepository extends JpaRepository<FocusSession, Long> {
}
