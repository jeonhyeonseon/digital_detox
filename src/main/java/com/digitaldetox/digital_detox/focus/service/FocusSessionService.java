package com.digitaldetox.digital_detox.focus.service;

import com.digitaldetox.digital_detox.focus.domain.FocusSession;
import com.digitaldetox.digital_detox.focus.dto.FocusSessionStartRequestDto;
import com.digitaldetox.digital_detox.focus.dto.FocusSessionStartResponseDto;
import com.digitaldetox.digital_detox.focus.repository.FocusSessionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class FocusSessionService {

    private final FocusSessionRepository focusSessionRepository;

    public FocusSessionStartResponseDto startSession(Long memberId, FocusSessionStartRequestDto sessionStartRequestDto) {

        if (sessionStartRequestDto.getTimeSet() == null || sessionStartRequestDto.getTimeSet() <= 0) {
            throw new IllegalArgumentException("설정 시간은 1분 이상이어야 합니다.");
        }

        FocusSession focusSession = new FocusSession(
                memberId,
                sessionStartRequestDto.getTimeSet(),
                LocalDateTime.now()
        );

        FocusSession savedSession = focusSessionRepository.save(focusSession);

        return new FocusSessionStartResponseDto(
                savedSession.getFocusSessionId(),
                savedSession.getTimeSet(),
                savedSession.getSessionStatus(),
                savedSession.getStartedAt(),
                savedSession.getEndedAt()
        );
    }
}
