package com.digitaldetox.digital_detox.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeeklyStatDto {

    private LocalDate date;
    private int screenTime;
    private int focusTime;

}
