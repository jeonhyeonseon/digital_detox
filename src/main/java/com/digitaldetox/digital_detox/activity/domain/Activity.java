package com.digitaldetox.digital_detox.activity.domain;

import com.digitaldetox.digital_detox.diary.domain.Mood;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_activity")
@Getter
@NoArgsConstructor
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long activityId;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Mood mood;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private ActivityType activityType;

    private String description;

}
