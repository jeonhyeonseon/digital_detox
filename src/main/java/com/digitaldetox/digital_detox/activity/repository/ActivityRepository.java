package com.digitaldetox.digital_detox.activity.repository;

import com.digitaldetox.digital_detox.activity.domain.Activity;
import com.digitaldetox.digital_detox.diary.domain.Mood;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

    List<Activity> findByMood(Mood mood);

}
