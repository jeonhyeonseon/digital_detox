package com.digitaldetox.digital_detox.challenge.service;

import com.digitaldetox.digital_detox.challenge.domain.*;
import com.digitaldetox.digital_detox.challenge.dto.*;
import com.digitaldetox.digital_detox.challenge.repository.ChallengeCertificationRepository;
import com.digitaldetox.digital_detox.challenge.repository.ChallengeMissionRepository;
import com.digitaldetox.digital_detox.challenge.repository.ChallengeRepository;
import com.digitaldetox.digital_detox.challenge.repository.MemberChallengeRepository;
import com.digitaldetox.digital_detox.member.entity.Member;
import com.digitaldetox.digital_detox.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ChallengeService {

    private final ChallengeRepository challengeRepository;
    private final ChallengeMissionRepository challengeMissionRepository;
    private final MemberChallengeRepository memberChallengeRepository;
    private final MemberRepository memberRepository;
    private final ChallengeCertificationRepository challengeCertificationRepository;

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

    public ChallengeJoinResponseDto joinChallenge(Long challengeId, Long memberId) {

        Challenge challenge = challengeRepository.findById(challengeId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 챌린지입니다."));

        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원정보입니다."));

        boolean alreadyChallengeJoined = memberChallengeRepository.existsByMemberAndChallengeAndMemberChallengeStatus(member, challenge, MemberChallengeStatus.IN_PROGRESS);

        if (alreadyChallengeJoined) {
            throw new IllegalArgumentException("이미 참여 중인 챌린지입니다. 참여 완료 후 다시 도전하세요!");
        }

        MemberChallenge memberChallenge = new MemberChallenge(
                null,
                member,
                challenge,
                LocalDate.now(),
                1,
                MemberChallengeStatus.IN_PROGRESS,
                0
        );

        MemberChallenge savedMemberChallenge = memberChallengeRepository.save(memberChallenge);

        return new ChallengeJoinResponseDto(savedMemberChallenge.getMemberChallengeId(), true);
    }

    public List<OngoingChallengeResponseDto> getOngoingChallenge(Long memberId) {

        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원정보입니다."));

        List<MemberChallenge> memberChallenges = memberChallengeRepository.findAllByMemberAndMemberChallengeStatus(member, MemberChallengeStatus.IN_PROGRESS);

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

    public ChallengeCertificationResponseDto challengeCertification(Long memberChallengeId, Long memberId, ChallengeCertificationRequestDto certificationRequestDto) {

        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원 정보입니다."));

        MemberChallenge memberChallenge = memberChallengeRepository.findByMemberChallengeIdAndMember(memberChallengeId, member);
        if (memberChallenge.getMemberChallengeStatus() != MemberChallengeStatus.IN_PROGRESS) {
            throw new IllegalArgumentException("진행 중인 챌린지만 인증할 수 있습니다.");
        }

        int currentDay = memberChallenge.getCurrentDay();
        int certificationDay = certificationRequestDto.getDayNumber();
        if (currentDay != certificationDay) {
            throw new IllegalArgumentException("진행 중인 챌린지만 인증할 수 있습니다.");
        }

        boolean alreadyCertified = challengeCertificationRepository.existsByMemberChallengeAndDayNumber(memberChallenge, certificationDay);
        if (alreadyCertified) {
            throw new IllegalArgumentException("이미 인증된 챌린지입니다.");
        }

        boolean hasReview = certificationRequestDto.getReviewContent() != null && certificationRequestDto.getReviewContent().trim().isEmpty();
        if (!hasReview) {
            throw new IllegalArgumentException("후기를 작성해야 합니다.");
        }

        return null;
    }
}
