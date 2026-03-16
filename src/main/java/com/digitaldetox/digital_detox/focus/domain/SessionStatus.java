package com.digitaldetox.digital_detox.focus.domain;

import lombok.Getter;

@Getter
public enum SessionStatus {

    IN_PROGRESS("진행중"),
    PAUSED("일시정지"),
    COMPLETED("완료"),
    CANCELLED("취소");

    private final String description;

    SessionStatus(String description) {
        this.description = description;
    }
}
