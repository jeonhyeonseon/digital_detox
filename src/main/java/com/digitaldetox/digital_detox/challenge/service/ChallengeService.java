package com.digitaldetox.digital_detox.challenge.service;

import com.digitaldetox.digital_detox.challenge.domain.Challenge;
import com.digitaldetox.digital_detox.challenge.dto.ChallengeListResponseDto;
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
}
