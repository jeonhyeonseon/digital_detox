package com.digitaldetox.digital_detox.diary.domain;

import lombok.Getter;

@Getter
public enum Mood {
    HAPPY("HAPPY", "😊"),
    NORMAL("NORMAL", "🙂"),
    SAD("SAD", "😢"),
    ANGRY("ANGRY", "😡"),
    TIRED("TIRED", "🥱");

    private String moodName;
    private String emoji;

    Mood(String moodName, String emoji) {
        this.moodName = moodName;
        this.emoji = emoji;
    }
}
