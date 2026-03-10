package com.digitaldetox.digital_detox.challenge.repository;

import com.digitaldetox.digital_detox.challenge.domain.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
}
