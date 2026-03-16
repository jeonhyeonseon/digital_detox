package com.digitaldetox.digital_detox.focus.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_focus_session")
@Getter
@NoArgsConstructor
public class FocusSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long focusSessionId;

    private Long memberId; // TODO

    private Integer timeSet; // 시간 설정(분)

    private Integer actualTime; // 실제 진행한 시간(분)

    @Enumerated(EnumType.STRING)
    private SessionStatus sessionStatus;

    private LocalDateTime startedAt;

    private LocalDateTime endedAt;

    public FocusSession(Long memberId, Integer timeSet, LocalDateTime startedAt) {
        this.memberId = memberId;
        this.timeSet = timeSet;
        this.actualTime = 0;
        this.sessionStatus = SessionStatus.IN_PROGRESS;
        this.startedAt = startedAt;
    }

    public void complete(Integer actualTime) {
        this.actualTime = actualTime;
        this.sessionStatus = SessionStatus.COMPLETED;
        this.endedAt = LocalDateTime.now();
    }
}
