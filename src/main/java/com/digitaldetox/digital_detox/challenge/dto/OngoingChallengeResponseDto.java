package com.digitaldetox.digital_detox.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OngoingChallengeResponseDto {

    private Long memberChallengeId;
    private Long challengeId;
    private String challengeTitle;
    private int currentDay;
    private int totalDays;
    private int streak;

}
