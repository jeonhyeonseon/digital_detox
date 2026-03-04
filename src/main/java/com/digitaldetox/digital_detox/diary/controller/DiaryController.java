package com.digitaldetox.digital_detox.diary.controller;

import com.digitaldetox.digital_detox.diary.dto.DiaryRegisterRequestDto;
import com.digitaldetox.digital_detox.diary.service.DiaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/diary")
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;

    @PostMapping
    public Map<String, Long> registerDiary(@RequestBody DiaryRegisterRequestDto diaryRegisterRequestDto) {

        Long diaryId = diaryService.registerDiary(diaryRegisterRequestDto);

        return Map.of("diaryId", diaryId);
    }
}
