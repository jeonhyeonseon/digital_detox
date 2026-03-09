package com.digitaldetox.digital_detox.diary.controller;

import com.digitaldetox.digital_detox.diary.dto.DiaryRegisterRequestDto;
import com.digitaldetox.digital_detox.diary.dto.DiaryUpdateRequestDto;
import com.digitaldetox.digital_detox.diary.dto.DiaryMonthResponseDto;
import com.digitaldetox.digital_detox.diary.service.DiaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/diary")
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;

    @GetMapping("/month")
    public List<DiaryMonthResponseDto> monthResponseDtoList(@RequestParam Long memberId,
                                                            @RequestParam int year,
                                                            @RequestParam int month) {

        return diaryService.getMonthlyDiary(memberId, year, month);
    }


    @PostMapping
    public Map<String, Long> registerDiary(@RequestBody DiaryRegisterRequestDto diaryRegisterRequestDto) {

        Long diaryId = diaryService.registerDiary(diaryRegisterRequestDto);

        return Map.of("diaryId", diaryId);
    }

    @PutMapping("/{diaryId}")
    public Map<String, Boolean> editDiary(@PathVariable Long diaryId,
                                          @RequestBody DiaryUpdateRequestDto updateRequestDto) {

        diaryService.updateDiary(diaryId, updateRequestDto);

        return Map.of("updated", true);
    }

    @DeleteMapping("/{diaryId}")
    public Map<String, Boolean> deleteDiary(@PathVariable Long diaryId) {

        diaryService.deleteDiary(diaryId);

        return Map.of("deleted", true);
    }
}
