package com.digitaldetox.digital_detox.focus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FocusSessionTodayRecordResponseDto {

    private Long completedCount;
    private int totalFocusTime;

}
