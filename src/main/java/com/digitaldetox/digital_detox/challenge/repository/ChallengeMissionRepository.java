package com.digitaldetox.digital_detox.challenge.repository;

import com.digitaldetox.digital_detox.challenge.domain.Challenge;
import com.digitaldetox.digital_detox.challenge.domain.ChallengeMission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChallengeMissionRepository extends JpaRepository<ChallengeMission, Long> {

    List<ChallengeMission> findAllByChallengeOrderByDayNumberAsc(Challenge challenge);
}
