package com.digitaldetox.digital_detox.challenge.repository;

import com.digitaldetox.digital_detox.challenge.domain.Challenge;
import com.digitaldetox.digital_detox.challenge.domain.MemberChallenge;
import com.digitaldetox.digital_detox.challenge.domain.MemberChallengeStatus;
import com.digitaldetox.digital_detox.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberChallengeRepository extends JpaRepository<MemberChallenge, Long> {

    boolean existsByMemberAndChallengeAndMemberChallengeStatus(Member member, Challenge challenge, MemberChallengeStatus memberChallengeStatus);

    List<MemberChallenge> findAllByMemberAndMemberChallengeStatus(Member member, MemberChallengeStatus memberChallengeStatus);
}
