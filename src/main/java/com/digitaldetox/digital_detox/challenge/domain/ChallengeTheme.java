package com.digitaldetox.digital_detox.challenge.domain;

import lombok.Getter;

@Getter
public enum ChallengeTheme {
    READING("READING", "독서"),
    EXERCISE("EXERCISE", "운동"),
    HOBBY("HOBBY", "취미"),
    SOCIAL("SOCIAL", "사람");

    private String challengeName;
    private String displayChallengeName;

    ChallengeTheme(String challengeName, String displayChallengeName) {
        this.challengeName = challengeName;
        this.displayChallengeName = displayChallengeName;
    }
}
