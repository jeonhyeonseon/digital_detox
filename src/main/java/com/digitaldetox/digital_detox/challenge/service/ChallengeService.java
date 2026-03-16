package com.digitaldetox.digital_detox.challenge.service;

import com.digitaldetox.digital_detox.challenge.domain.Challenge;
import com.digitaldetox.digital_detox.challenge.domain.ChallengeMission;
import com.digitaldetox.digital_detox.challenge.domain.MemberChallenge;
import com.digitaldetox.digital_detox.challenge.domain.MemberChallengeStatus;
import com.digitaldetox.digital_detox.challenge.dto.ChallengeDetailResponseDto;
import com.digitaldetox.digital_detox.challenge.dto.ChallengeListResponseDto;
import com.digitaldetox.digital_detox.challenge.dto.ChallengeMissionResponseDto;
import com.digitaldetox.digital_detox.challenge.dto.OngoingChallengeResponseDto;
import com.digitaldetox.digital_detox.challenge.repository.ChallengeMissionRepository;
import com.digitaldetox.digital_detox.challenge.repository.ChallengeRepository;
import com.digitaldetox.digital_detox.challenge.repository.MemberChallengeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ChallengeService {

    private final ChallengeRepository challengeRepository;
    private final ChallengeMissionRepository challengeMissionRepository;
    private final MemberChallengeRepository memberChallengeRepository;

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

    public void joinChallenge(Long challengeId, Long memberId) {

        Challenge challenge = challengeRepository.findById(challengeId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 챌린지입니다."));

        boolean alreadyChallengeJoined = memberChallengeRepository.existsByMemberIdAndChallengeAndMemberChallengeStatus(memberId, challenge, MemberChallengeStatus.IN_PROGRESS);

        if (alreadyChallengeJoined) {
            throw new IllegalArgumentException("이미 참여 중인 챌린지입니다. 참여 완료 후 다시 도전하세요!");
        }

        MemberChallenge memberChallenge = new MemberChallenge(
                null,
                memberId,
                challenge,
                LocalDate.now(),
                MemberChallengeStatus.IN_PROGRESS,
                0
        );

        memberChallengeRepository.save(memberChallenge);
    }

    public List<OngoingChallengeResponseDto> getOngoingChallenge(Long memberId) {

        List<MemberChallenge> memberChallenges = memberChallengeRepository.findAllByMemberIdAndMemberChallengeStatus(memberId, MemberChallengeStatus.IN_PROGRESS);

        LocalDate today = LocalDate.now();

        return memberChallenges.stream().map(memberChallenge ->  {
            Challenge challenge = memberChallenge.getChallenge();

            int totalDays = challenge.getDurationDays();
            int currentDay = (int) ChronoUnit.DAYS.between(memberChallenge.getStartDate(), today) + 1;

            currentDay = Math.max(1, currentDay);
            currentDay = Math.min(currentDay, totalDays);

            return new OngoingChallengeResponseDto(
                    memberChallenge.getMemberChallengeId(),
                    challenge.getChallengeId(),
                    challenge.getTitle(),
                    currentDay,
                    totalDays,
                    memberChallenge.getStreak()
            );
        }).toList();
    }
}
