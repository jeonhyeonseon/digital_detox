package com.digitaldetox.digital_detox.diary.repository;

import com.digitaldetox.digital_detox.diary.domain.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {

    List<Diary> findAllByMemberIdAndDiaryDateBetween(Long memberId, LocalDate startDate, LocalDate endDate);

    Optional<Diary> findByMemberIdAndDiaryDate(Long memberId, LocalDate diaryDate);
}
