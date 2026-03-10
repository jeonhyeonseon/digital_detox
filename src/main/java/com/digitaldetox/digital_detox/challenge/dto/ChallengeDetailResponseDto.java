package com.digitaldetox.digital_detox.challenge.dto;

import com.digitaldetox.digital_detox.challenge.domain.ChallengeTheme;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChallengeDetailResponseDto {

    private Long challengeId;
    private String title;
    private String description;
    private int durationDays;
    private int difficulty;
    private ChallengeTheme challengeTheme;
    private String expectedEffect;
    private String badge;

    private List<ChallengeMissionResponseDto> challengeMissionResponseDto;

}
