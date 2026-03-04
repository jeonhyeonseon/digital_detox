package com.digitaldetox.digital_detox.diary.dto;

import com.digitaldetox.digital_detox.diary.domain.Diary;
import com.digitaldetox.digital_detox.diary.domain.Mood;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiaryRegisterRequestDto {

    private Mood mood;
    private int screenTime;
    private String content;

    public Diary toDiary() {
        return Diary.builder()
                .mood(this.mood)
                .screenTime(this.screenTime)
                .content(this.content)
                .build();
    }
}
