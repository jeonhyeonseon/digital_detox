package com.digitaldetox.digital_detox.challenge.domain;

import lombok.Getter;

@Getter
public enum MemberChallengeStatus {

    IN_PROGRESS("IN_PROGRESS", "진행중"),
    COMPLETED("COMPLETED", "완료");

    private String challengeStatus;
    private String displayChallengeStatus;

    MemberChallengeStatus(String challengeStatus, String displayChallengeStatus) {
        this.challengeStatus = challengeStatus;
        this.displayChallengeStatus = displayChallengeStatus;
    }
}
