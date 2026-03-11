package com.digitaldetox.digital_detox.challenge.repository;

import com.digitaldetox.digital_detox.challenge.domain.MemberChallenge;
import com.digitaldetox.digital_detox.challenge.domain.MemberChallengeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberChallengeRepository extends JpaRepository<MemberChallenge, Long> {

    boolean existsByMemberIdAndChallengeAndStatus(Long challengeId, Long memberId, MemberChallengeStatus memberChallengeStatus);

    List<MemberChallenge> findAllByMemberIdAndCompletedFalse(Long memberId);
}
