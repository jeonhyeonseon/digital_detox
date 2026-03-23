package com.digitaldetox.digital_detox.focus.domain;

import com.digitaldetox.digital_detox.member.entity.Member;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private Integer timeSet; // 시간 설정(분)

    private Integer actualTime; // 실제 진행한 시간(분)

    @Enumerated(EnumType.STRING)
    private SessionStatus sessionStatus;

    private LocalDateTime startedAt;

    private LocalDateTime endedAt;

    public FocusSession(Member member, Integer timeSet, LocalDateTime startedAt) {
        this.member = member;
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

    public void cancel(Integer actualTime) {
        this.actualTime = actualTime;
        this.sessionStatus = SessionStatus.CANCELLED;
        this.endedAt = LocalDateTime.now();
    }
}
