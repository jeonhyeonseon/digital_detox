package com.digitaldetox.digital_detox.focus.dto;

import com.digitaldetox.digital_detox.focus.domain.SessionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FocusSessionStartResponseDto {

    private Long focusSessionId;
    private Integer timeSet;
    private SessionStatus sessionStatus;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;

}
