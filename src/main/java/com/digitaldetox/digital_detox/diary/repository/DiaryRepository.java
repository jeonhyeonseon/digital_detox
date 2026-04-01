package com.digitaldetox.digital_detox.diary.repository;

import com.digitaldetox.digital_detox.diary.domain.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {

    List<Diary> findAllByMember_MemberIdAndDiaryDateBetween(Long memberId, LocalDate startDate, LocalDate endDate);

    Optional<Diary> findByMember_MemberIdAndDiaryDate(Long memberId, LocalDate diaryDate);

    Optional<Diary> findByDiaryIdAndMember_MemberId(Long diaryId, Long memberId);

    @Query("""
            select d
            from Diary d
            where d.member.memberId = :memberId
            and d.diaryDate between :startDateTime and :endDateTime
            order by d.diaryDate asc
            """)
    List<Diary> findWeeklyDiaries(Long memberId, LocalDate weekStart, LocalDate today);
}
