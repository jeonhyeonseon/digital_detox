package com.digitaldetox.digital_detox.challenge.controller;

import com.digitaldetox.digital_detox.auth.service.CustomUserDetails;
import com.digitaldetox.digital_detox.challenge.dto.*;
import com.digitaldetox.digital_detox.challenge.service.ChallengeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{challengeId}")
    public ChallengeDetailResponseDto detailChallenge(@PathVariable Long challengeId) {

        return challengeService.getChallengeDetail(challengeId);
    }

    @PostMapping("/{challengeId}/join")
    public ChallengeJoinResponseDto joinChallenge(@PathVariable Long challengeId,
                                                  @AuthenticationPrincipal CustomUserDetails customUserDetails) {

        return challengeService.joinChallenge(challengeId, customUserDetails.getMemberId());
    }

    @GetMapping("/ongoing")
    public List<OngoingChallengeResponseDto> ongoingChallengeResponseDto(@AuthenticationPrincipal CustomUserDetails customUserDetails) {

        return challengeService.getOngoingChallenge(customUserDetails.getMemberId());
    }
}
