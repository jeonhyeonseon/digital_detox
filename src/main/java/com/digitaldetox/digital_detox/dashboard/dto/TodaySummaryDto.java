package com.digitaldetox.digital_detox.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodaySummaryDto {

    private int screenTime;
    private int focusTime;
    private int level;

}
