package com.digitaldetox.digital_detox.activity.service;

import com.digitaldetox.digital_detox.activity.domain.Activity;
import com.digitaldetox.digital_detox.activity.domain.ActivityType;
import com.digitaldetox.digital_detox.activity.dto.ActivityItemDto;
import com.digitaldetox.digital_detox.activity.dto.ActivityResponseDto;
import com.digitaldetox.digital_detox.activity.repository.ActivityRepository;
import com.digitaldetox.digital_detox.diary.domain.Mood;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository activityRepository;

    public ActivityResponseDto getActivity(Mood mood) {

        List<Activity> activityList = activityRepository.findByMood(mood);

        List<ActivityItemDto> walkActivity = activityList.stream()
                .filter(activity -> activity.getActivityType() == ActivityType.WALK)
                .map(ActivityItemDto::fromActivity)
                .toList();

        List<ActivityItemDto> bookActivity = activityList.stream()
                .filter(activity -> activity.getActivityType() == ActivityType.BOOK)
                .map(ActivityItemDto::fromActivity)
                .toList();

        List<ActivityItemDto> exhibitionActivity = activityList.stream()
                .filter(activity -> activity.getActivityType() == ActivityType.EXHIBITION)
                .map(ActivityItemDto::fromActivity)
                .toList();

        List<ActivityItemDto> journalingActivity = activityList.stream()
                .filter(activity -> activity.getActivityType() == ActivityType.JOURNALING)
                .map(ActivityItemDto::fromActivity)
                .toList();

        return new ActivityResponseDto(
                mood,
                walkActivity,
                bookActivity,
                exhibitionActivity,
                journalingActivity
        );
    }

}
