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

    @Column(nullable = false)
    private int screenTime;

    // 스크린타임 어제와 비교
    @Column(nullable = false)
    private int screenTimeDifference;
    private String content;

    public void updateDiary(Mood mood, int screenTime, String content) {
        this.mood = mood;
        this.screenTime = screenTime;
        this.content = content;
    }
}
