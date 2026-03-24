package com.digitaldetox.digital_detox.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChallengeJoinResponseDto {

    private Long memberChallengeId;
    private boolean joined;

}
