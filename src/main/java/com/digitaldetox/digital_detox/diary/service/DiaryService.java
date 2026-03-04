package com.digitaldetox.digital_detox.diary.service;

import com.digitaldetox.digital_detox.diary.dto.DiaryRegisterRequestDto;
import com.digitaldetox.digital_detox.diary.domain.Diary;
import com.digitaldetox.digital_detox.diary.repository.DiaryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;

    public Long registerDiary(DiaryRegisterRequestDto diaryRegisterRequestDto) {

        Diary diary = diaryRegisterRequestDto.toDiary();
        Diary saved = diaryRepository.save(diary);

        return saved.getDiaryId();
    }
}
