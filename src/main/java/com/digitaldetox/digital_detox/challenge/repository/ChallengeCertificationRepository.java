package com.digitaldetox.digital_detox.challenge.repository;

import com.digitaldetox.digital_detox.challenge.domain.ChallengeCertification;
import com.digitaldetox.digital_detox.challenge.domain.MemberChallenge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeCertificationRepository extends JpaRepository<ChallengeCertification, Long> {

    boolean existsByMemberChallengeAndDayNumber(MemberChallenge memberChallenge, int certificationDay);

}
