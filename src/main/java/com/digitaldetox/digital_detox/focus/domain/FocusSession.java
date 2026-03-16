package com.digitaldetox.digital_detox.focus.domain;

import com.digitaldetox.digital_detox.focus.dto.FocusSessionStartRequestDto;
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

}
