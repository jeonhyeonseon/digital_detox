package com.digitaldetox.digital_detox.badge.domain;

import lombok.Getter;

@Getter
public enum BadgeType {

    DETOX_BEGINNER("디톡스 입문자", 7),
    DETOX_PRACTITIONER("디톡스 실천가", 14),
    DIGITAL_MASTER("디지털 균형 마스터", 30);

    private String badgeName;
    private int durationDays;

    BadgeType(String badgeName, int durationDays) {
        this.badgeName = badgeName;
        this.durationDays = durationDays;
    }
}
