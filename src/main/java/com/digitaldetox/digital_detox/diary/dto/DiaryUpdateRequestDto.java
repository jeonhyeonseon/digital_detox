package com.digitaldetox.digital_detox.diary.dto;

import com.digitaldetox.digital_detox.diary.domain.Mood;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiaryUpdateRequestDto {

    private Mood mood;
    private int screenTime;
    private String content;

}
