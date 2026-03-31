package com.digitaldetox.digital_detox.dashboard.service;

import com.digitaldetox.digital_detox.dashboard.dto.DashboardResponseDto;
import com.digitaldetox.digital_detox.dashboard.dto.TodaySummaryDto;
import com.digitaldetox.digital_detox.diary.domain.Diary;
import com.digitaldetox.digital_detox.diary.repository.DiaryRepository;
import com.digitaldetox.digital_detox.focus.repository.FocusSessionRepository;
import com.digitaldetox.digital_detox.member.entity.Member;
import com.digitaldetox.digital_detox.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class DashboardService {

    private final DiaryRepository diaryRepository;
    private final FocusSessionRepository focusSessionRepository;
    private final MemberRepository memberRepository;

    public DashboardResponseDto getDashboard(Long memberId) {

        LocalDate today = LocalDate.now();

        TodaySummaryDto todaySummaryDto = getTodaySummary(memberId, today);

        return null;
    }

    // 오늘의 요약
    private TodaySummaryDto getTodaySummary(Long memberId, LocalDate today) {

        int screenTime = diaryRepository.findByMember_MemberIdAndDiaryDate(memberId, today)
                                        .map(Diary::getScreenTime)
                                        .orElse(0);

        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1);

        int focusTime = focusSessionRepository.sumTodayFocusTime(memberId, startOfDay, endOfDay);

        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원정보입니다."));

        return new TodaySummaryDto(screenTime, focusTime, member.getLevel());
    }
}

