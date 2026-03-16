package com.digitaldetox.digital_detox.focus.controller;

import com.digitaldetox.digital_detox.focus.dto.FocusSessionStartRequestDto;
import com.digitaldetox.digital_detox.focus.dto.FocusSessionStartResponseDto;
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
}
