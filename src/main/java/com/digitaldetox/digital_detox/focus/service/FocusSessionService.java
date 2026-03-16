package com.digitaldetox.digital_detox.focus.service;

import com.digitaldetox.digital_detox.focus.domain.FocusSession;
import com.digitaldetox.digital_detox.focus.domain.SessionStatus;
import com.digitaldetox.digital_detox.focus.dto.*;
import com.digitaldetox.digital_detox.focus.repository.FocusSessionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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

    public FocusSessionEndResponseDto endSession(Long focusSessionId, FocusSessionEndRequestDto sessionEndRequestDto) {

        FocusSession focusSession = focusSessionRepository.findById(focusSessionId).orElseThrow(() -> new IllegalArgumentException("해당 포커스 세션이 존재하지 않습니다."));

        if (focusSession.getSessionStatus() != SessionStatus.IN_PROGRESS) {
            throw new IllegalArgumentException("진행 중인 세션만 종료할 수 있습니다.");
        }

        if (sessionEndRequestDto.getActualTime() == null || sessionEndRequestDto.getActualTime() < 0) {
            throw new IllegalArgumentException("실제 진행 시간은 0분 이상이어야 합니다.");
        }

        if (sessionEndRequestDto.getActualTime() > focusSession.getTimeSet()) {
            throw new IllegalArgumentException("실제 진행 시간은 설정된 시간을 초과할 수 없습니다.");
        }

        focusSession.complete(sessionEndRequestDto.getActualTime());

        return new FocusSessionEndResponseDto(
                focusSession.getFocusSessionId(),
                focusSession.getTimeSet(),
                focusSession.getActualTime(),
                focusSession.getSessionStatus(),
                focusSession.getStartedAt(),
                focusSession.getEndedAt()
        );
    }

    public FocusSessionCancelResponseDto cancelSession(Long focusSessionId, FocusSessionEndRequestDto sessionEndRequestDto) {

        FocusSession focusSession = focusSessionRepository.findById(focusSessionId).orElseThrow(() -> new IllegalArgumentException("해당 포커스 세션이 존재하지 않습니다."));

        if (focusSession.getSessionStatus() != SessionStatus.IN_PROGRESS) {
            throw new IllegalArgumentException("진행 중인 세션만 취소할 수 있습니다.");
        }

        if (sessionEndRequestDto.getActualTime() == null || sessionEndRequestDto.getActualTime() < 0) {
            throw new IllegalArgumentException("실제 진행 시간은 0분 이상이어야 합니다.");
        }

        if (sessionEndRequestDto.getActualTime() > focusSession.getTimeSet()) {
            throw new IllegalArgumentException("실제 진행 시간은 설정된 시간을 초과할 수 없습니다.");
        }

        focusSession.cancel(sessionEndRequestDto.getActualTime());

        return new FocusSessionCancelResponseDto(
                focusSession.getFocusSessionId(),
                focusSession.getTimeSet(),
                focusSession.getActualTime(),
                focusSession.getSessionStatus(),
                focusSession.getStartedAt(),
                focusSession.getEndedAt()
        );
    }

    public FocusSessionTodayRecordResponseDto todaySession(Long memberId) {

        LocalDateTime startOfDay = LocalDateTime.now().toLocalDate().atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1);

        List<FocusSession> completedSession = focusSessionRepository.findByMemberIdAndSessionStatusAndStartedAtBetween(memberId, SessionStatus.COMPLETED, startOfDay, endOfDay);

        long completedCount = completedSession.size();

        int totalFocusTime = completedSession.stream().mapToInt(FocusSession::getActualTime).sum();

        return new FocusSessionTodayRecordResponseDto(completedCount, totalFocusTime);
    }
}
