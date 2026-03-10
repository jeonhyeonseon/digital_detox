package com.digitaldetox.digital_detox.challenge.dto;

import com.digitaldetox.digital_detox.challenge.domain.ChallengeTheme;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChallengeListResponseDto {

    private Long challengeId;
    private String title;
    private int durationDays;
    private int difficulty;
    private ChallengeTheme challengeTheme;

}
