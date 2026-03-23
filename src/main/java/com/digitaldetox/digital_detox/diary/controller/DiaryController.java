package com.digitaldetox.digital_detox.diary.controller;

import com.digitaldetox.digital_detox.auth.service.CustomUserDetails;
import com.digitaldetox.digital_detox.diary.dto.DiaryDateResponseDto;
import com.digitaldetox.digital_detox.diary.dto.DiaryRegisterRequestDto;
import com.digitaldetox.digital_detox.diary.dto.DiaryUpdateRequestDto;
import com.digitaldetox.digital_detox.diary.dto.DiaryMonthResponseDto;
import com.digitaldetox.digital_detox.diary.service.DiaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/diary")
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;

    @GetMapping("/month")
    public List<DiaryMonthResponseDto> monthResponseDtoList(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                                            @RequestParam int year,
                                                            @RequestParam int month) {

        Long memberId = customUserDetails.getMemberId();

        return diaryService.getMonthlyDiary(memberId, year, month);
    }

    @GetMapping("/date")
    public DiaryDateResponseDto dateResponseDto(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                                @RequestParam LocalDate diaryDate) {

        Long memberId = customUserDetails.getMemberId();

        return diaryService.getDiaryByDate(memberId, diaryDate);
    }

    @PostMapping
    public Map<String, Long> registerDiary(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                           @RequestBody DiaryRegisterRequestDto diaryRegisterRequestDto) {

        Long memberId = customUserDetails.getMemberId();

        Long diaryId = diaryService.registerDiary(memberId, diaryRegisterRequestDto);

        return Map.of("diaryId", diaryId);
    }

    @PutMapping("/{diaryId}")
    public Map<String, Boolean> editDiary(@PathVariable Long diaryId,
                                          @AuthenticationPrincipal CustomUserDetails customUserDetails,
                                          @RequestBody DiaryUpdateRequestDto updateRequestDto) {

        Long memberId = customUserDetails.getMemberId();

        diaryService.updateDiary(diaryId, memberId, updateRequestDto);

        return Map.of("updated", true);
    }

    @DeleteMapping("/{diaryId}")
    public Map<String, Boolean> deleteDiary(@PathVariable Long diaryId,
                                            @AuthenticationPrincipal CustomUserDetails customUserDetails) {

        Long memberId = customUserDetails.getMemberId();

        diaryService.deleteDiary(diaryId, memberId);

        return Map.of("deleted", true);
    }
}
