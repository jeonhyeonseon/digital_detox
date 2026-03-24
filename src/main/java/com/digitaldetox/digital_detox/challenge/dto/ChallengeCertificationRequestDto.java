package com.digitaldetox.digital_detox.challenge.dto;

import com.digitaldetox.digital_detox.diary.domain.Mood;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChallengeCertificationRequestDto {

    private int dayNumber;
    private String reviewContent;
    private Mood mood;

}
