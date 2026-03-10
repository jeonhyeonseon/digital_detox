package com.digitaldetox.digital_detox.diary.service;

import com.digitaldetox.digital_detox.diary.dto.DiaryDateResponseDto;
import com.digitaldetox.digital_detox.diary.dto.DiaryRegisterRequestDto;
import com.digitaldetox.digital_detox.diary.domain.Diary;
import com.digitaldetox.digital_detox.diary.dto.DiaryUpdateRequestDto;
import com.digitaldetox.digital_detox.diary.dto.DiaryMonthResponseDto;
import com.digitaldetox.digital_detox.diary.repository.DiaryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;

    public List<DiaryMonthResponseDto> getMonthlyDiary(Long memberId, int year, int month) {

        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate startDate = yearMonth.atDay(1);
        LocalDate endDate = yearMonth.atEndOfMonth();

        List<Diary> diaries = diaryRepository.findAllByMemberIdAndDiaryDateBetween(memberId, startDate, endDate);

        return diaries.stream().map(diary -> new DiaryMonthResponseDto(
                                                                            diary.getDiaryId(),
                                                                            diary.getDiaryDate(),
                                                                            diary.getMood()
                                                                            )).toList();

    }

    public DiaryDateResponseDto getDiaryByDate(Long memberId, LocalDate diaryDate) {

        Diary diary = diaryRepository.findByMemberIdAndDiaryDate(memberId, diaryDate)
                                    .orElseThrow(() -> new IllegalArgumentException("해당 날짜의 다이어리가 존재하지 않습니다."));

        LocalDate yesterday = diaryDate.minusDays(1);

        Integer screenTimeDifference = diaryRepository.findByMemberIdAndDiaryDate(memberId, diaryDate)
                                                        .map(yesterdayDiary -> diary.getScreenTime() - yesterdayDiary.getScreenTime())
                                                        .orElse(null);

        return new DiaryDateResponseDto(
                                        diary.getDiaryId(),
                                        diary.getDiaryDate(),
                                        diary.getMood(),
                                        diary.getScreenTime(),
                                        screenTimeDifference,
                                        diary.getContent()
                                        );
    }

    public Long registerDiary(DiaryRegisterRequestDto diaryRegisterRequestDto) {

        Diary diary = diaryRegisterRequestDto.toDiary();
        Diary saved = diaryRepository.save(diary);

        return saved.getDiaryId();
    }

    public void updateDiary(Long diaryId, DiaryUpdateRequestDto updateRequestDto) {

        Diary diary = diaryRepository.findById(diaryId)
                        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 다이어리입니다."));

        diary.updateDiary(updateRequestDto.getMood(), updateRequestDto.getScreenTime(), updateRequestDto.getContent());
    }

    public void deleteDiary(Long diaryId) {

        Diary diary = diaryRepository.findById(diaryId)
                        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 다이어리입니다."));

        diaryRepository.delete(diary);
    }
}
