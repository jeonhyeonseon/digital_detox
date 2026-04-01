package com.digitaldetox.digital_detox.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrentChallengeDto {

    private Long challengeId;
    private String title;
    private int progressDay;
    private int totalDay;

}
