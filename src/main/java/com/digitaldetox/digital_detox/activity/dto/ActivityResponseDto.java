package com.digitaldetox.digital_detox.activity.dto;

import com.digitaldetox.digital_detox.diary.domain.Mood;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityResponseDto {

    private Mood mood;
    private List<ActivityItemDto> walkActivity;
    private List<ActivityItemDto> bookActivity;
    private List<ActivityItemDto> exhibitionActivity;
    private List<ActivityItemDto> journalingActivity;

}
