package com.digitaldetox.digital_detox.challenge.controller;

import com.digitaldetox.digital_detox.challenge.dto.ChallengeListResponseDto;
import com.digitaldetox.digital_detox.challenge.service.ChallengeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/challenge")
@RequiredArgsConstructor
public class ChallengeController {

    private final ChallengeService challengeService;

    @GetMapping
    public List<ChallengeListResponseDto> getChallenge() {

        return challengeService.getChallengeList();
    }
}
