package com.digitaldetox.digital_detox.activity.controller;

import com.digitaldetox.digital_detox.activity.dto.ActivityResponseDto;
import com.digitaldetox.digital_detox.activity.service.ActivityService;
import com.digitaldetox.digital_detox.diary.domain.Mood;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/activity")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    @GetMapping
    public ResponseEntity<ActivityResponseDto> getActivity(@RequestParam Mood mood) {

        ActivityResponseDto activityResponseDto = activityService.getActivity( mood);

        return ResponseEntity.ok(activityResponseDto);
    }
}
