package com.digitaldetox.digital_detox.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardResponseDto {

    private TodaySummaryDto todaySummaryDto;
    private List<WeeklyStatDto> weeklyStatDtoList;

}
