package com.digitaldetox.digital_detox.diary.domain;

import com.digitaldetox.digital_detox.member.domain.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "tbl_diary")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long diaryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDate diaryDate;

    @Enumerated(EnumType.STRING)
    private Mood mood;

    @Column(nullable = false)
    private int screenTime;

    private String content;

    public void updateDiary(Mood mood, int screenTime, String content) {
        this.mood = mood;
        this.screenTime = screenTime;
        this.content = content;
    }
}
