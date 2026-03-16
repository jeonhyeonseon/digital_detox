package com.digitaldetox.digital_detox.challenge.repository;

import com.digitaldetox.digital_detox.challenge.domain.Challenge;
import com.digitaldetox.digital_detox.challenge.domain.MemberChallenge;
import com.digitaldetox.digital_detox.challenge.domain.MemberChallengeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberChallengeRepository extends JpaRepository<MemberChallenge, Long> {

    boolean existsByMemberIdAndChallengeAndMemberChallengeStatus(Long memberId, Challenge challenge, MemberChallengeStatus memberChallengeStatus);

    List<MemberChallenge> findAllByMemberIdAndMemberChallengeStatus(Long memberId, MemberChallengeStatus memberChallengeStatus);
}
