package com.digitaldetox.digital_detox.diary.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "tbl_diary")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long diaryId;

    private Long memberId; // TODO

    private LocalDate diaryDate;

    @Enumerated(EnumType.STRING)
    private Mood mood;
    private int screenTime;
    private String content;

    public void updateDiary(Mood mood, int screenTime, String content) {
        this.mood = mood;
        this.screenTime = screenTime;
        this.content = content;
    }
}
