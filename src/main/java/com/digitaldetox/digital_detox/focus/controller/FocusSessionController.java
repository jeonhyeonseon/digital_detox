package com.digitaldetox.digital_detox.focus.controller;

import com.digitaldetox.digital_detox.focus.dto.*;
import com.digitaldetox.digital_detox.focus.service.FocusSessionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/focus-session")
@RequiredArgsConstructor
public class FocusSessionController {

    private final FocusSessionService focusSessionService;

    @PostMapping
    public FocusSessionStartResponseDto sessionStart(@RequestParam Long memberId,
                                                     @RequestBody FocusSessionStartRequestDto sessionStartRequestDto) {

        return focusSessionService.startSession(memberId, sessionStartRequestDto);
    }

    @PatchMapping("/{focusSessionId}/complete")
    public FocusSessionEndResponseDto sessionEnd(@PathVariable Long focusSessionId,
                                                 @RequestBody FocusSessionEndRequestDto sessionEndRequestDto) {

        return focusSessionService.endSession(focusSessionId, sessionEndRequestDto);
    }

    @PatchMapping("/{focusSessionId}/cancel")
    public FocusSessionCancelResponseDto cancelSession(@PathVariable Long focusSessionId,
                                                       @RequestBody FocusSessionEndRequestDto sessionEndRequestDto) {

        return focusSessionService.cancelSession(focusSessionId, sessionEndRequestDto);
    }

    @GetMapping("/today")
    public FocusSessionTodayRecordResponseDto todaySession(@RequestParam Long memberId) {

        return focusSessionService.todaySession(memberId);
    }
}
