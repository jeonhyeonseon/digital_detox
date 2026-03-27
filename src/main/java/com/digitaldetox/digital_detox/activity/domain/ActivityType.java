package com.digitaldetox.digital_detox.activity.domain;

import lombok.Getter;

@Getter
public enum ActivityType {

    WALK("산책"),
    BOOK("책"),
    EXHIBITION("전시회"),
    JOURNALING("저널링");

    private String activityTypeName;

    ActivityType(String activityTypeName) {
        this.activityTypeName = activityTypeName;
    }

}
