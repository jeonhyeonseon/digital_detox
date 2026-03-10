package com.digitaldetox.digital_detox.diary.dto;

import com.digitaldetox.digital_detox.diary.domain.Mood;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiaryDateResponseDto {

    private Long diaryId;
    private LocalDate diaryDate;
    private Mood mood;
    private int screenTime;
    private int screenTimeDifference;
    private String content;

}
