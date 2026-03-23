package com.digitaldetox.digital_detox.diary.repository;

import com.digitaldetox.digital_detox.diary.domain.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {

    List<Diary> findAllByMember_MemberIdAndDiaryDateBetween(Long memberId, LocalDate startDate, LocalDate endDate);

    Optional<Diary> findByMember_MemberIdAndDiaryDate(Long memberId, LocalDate diaryDate);

    Optional<Diary> findByDiaryIdAndMember_MemberId(Long diaryId, Long memberId);
}
