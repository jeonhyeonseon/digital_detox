package com.digitaldetox.digital_detox.activity.dto;

import com.digitaldetox.digital_detox.activity.domain.Activity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityItemDto {

    private String description;

    public static ActivityItemDto fromActivity(Activity activity) {
        return new ActivityItemDto(
                activity.getDescription()
        );
    }
}
