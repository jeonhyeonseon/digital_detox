package com.digitaldetox.digital_detox.diary.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "tbl_diary")
@Setter(AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
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

}
