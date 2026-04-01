package com.digitaldetox.digital_detox.challenge.repository;

import com.digitaldetox.digital_detox.challenge.domain.Challenge;
import com.digitaldetox.digital_detox.challenge.domain.MemberChallenge;
import com.digitaldetox.digital_detox.challenge.domain.MemberChallengeStatus;
import com.digitaldetox.digital_detox.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberChallengeRepository extends JpaRepository<MemberChallenge, Long> {

    boolean existsByMemberAndChallengeAndMemberChallengeStatus(Member member, Challenge challenge, MemberChallengeStatus memberChallengeStatus);

    List<MemberChallenge> findAllByMemberAndMemberChallengeStatus(Member member, MemberChallengeStatus memberChallengeStatus);

    Optional<MemberChallenge> findByMemberChallengeIdAndMember(Long memberChallengeId, Member member);

    @Query("""
            select mc
            from MemberChallenge mc
            join fetch mc.challenge c
            where mc.member.memberId = :memberId
            and mc.memberChallengeStatus = :status
            """)
    Optional<MemberChallenge> findCurrentChallenge(Long memberId);
}
