package com.digitaldetox.digital_detox.challenge.service;

import com.digitaldetox.digital_detox.challenge.domain.Challenge;
import com.digitaldetox.digital_detox.challenge.domain.ChallengeMission;
import com.digitaldetox.digital_detox.challenge.dto.ChallengeDetailResponseDto;
import com.digitaldetox.digital_detox.challenge.dto.ChallengeListResponseDto;
import com.digitaldetox.digital_detox.challenge.dto.ChallengeMissionResponseDto;
import com.digitaldetox.digital_detox.challenge.repository.ChallengeMissionRepository;
import com.digitaldetox.digital_detox.challenge.repository.ChallengeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ChallengeService {

    private final ChallengeRepository challengeRepository;
    private final ChallengeMissionRepository challengeMissionRepository;

    public List<ChallengeListResponseDto> getChallengeList() {

        List<Challenge> challenges = challengeRepository.findAll();

        return challenges.stream()
                .map(challenge -> new ChallengeListResponseDto(
                            challenge.getChallengeId(),
                            challenge.getTitle(),
                            challenge.getDurationDays(),
                            challenge.getDifficulty(),
                            challenge.getChallengeTheme()
                )).toList();
    }

    public ChallengeDetailResponseDto getChallengeDetail(Long challengeId) {

        Challenge challenge = challengeRepository.findById(challengeId)
                                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 챌린지입니다."));

        List<ChallengeMission> challengeMissions = challengeMissionRepository.findAllByChallengeOrderByDayNumberAsc(challenge);

        List<ChallengeMissionResponseDto> challengeMissionResponseDto = challengeMissions.stream()
                    .map(challengeMission -> new ChallengeMissionResponseDto(
                            challengeMission.getDayNumber(),
                            challengeMission.getMissionContent()
                    )).toList();

        return new ChallengeDetailResponseDto(
                challenge.getChallengeId(),
                challenge.getTitle(),
                challenge.getDescription(),
                challenge.getDurationDays(),
                challenge.getDifficulty(),
                challenge.getChallengeTheme(),
                challenge.getExpectedEffect(),
                challenge.getBadge(),
                challengeMissionResponseDto
        );
    }
}
