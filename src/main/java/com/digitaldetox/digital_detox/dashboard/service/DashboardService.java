package com.digitaldetox.digital_detox.dashboard.service;

import com.digitaldetox.digital_detox.dashboard.dto.DashboardResponseDto;
import com.digitaldetox.digital_detox.dashboard.dto.TodaySummaryDto;
import com.digitaldetox.digital_detox.dashboard.dto.WeeklyStatDto;
import com.digitaldetox.digital_detox.diary.domain.Diary;
import com.digitaldetox.digital_detox.diary.repository.DiaryRepository;
import com.digitaldetox.digital_detox.focus.domain.FocusSession;
import com.digitaldetox.digital_detox.focus.repository.FocusSessionRepository;
import com.digitaldetox.digital_detox.member.entity.Member;
import com.digitaldetox.digital_detox.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class DashboardService {

    private final DiaryRepository diaryRepository;
    private final FocusSessionRepository focusSessionRepository;
    private final MemberRepository memberRepository;

    public DashboardResponseDto getDashboard(Long memberId) {

        LocalDate today = LocalDate.now();
        LocalDate weekStart = today.minusDays(6);

        TodaySummaryDto todaySummaryDto = getTodaySummary(memberId, today);
        List<WeeklyStatDto> weeklyStatDtoList = getWeeklyStat(memberId, weekStart, today);

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

    // 주간 통계
    public List<WeeklyStatDto> getWeeklyStat(Long memberId, LocalDate weekStart, LocalDate today) {

        List<Diary> diaries = diaryRepository.findWeeklyDiaries(memberId, weekStart, today);

        Map<LocalDate, Integer> screenTime = diaries.stream()
                .collect(Collectors.toMap(
                        Diary::getDiaryDate,
                        Diary::getScreenTime
                ));

        LocalDateTime startDateTime = weekStart.atStartOfDay();
        LocalDateTime endDateTime = today.plusDays(1).atStartOfDay();

        List<FocusSession> focusSessions = focusSessionRepository.findWeeklyFocusSessions(memberId, startDateTime, endDateTime);

        Map<LocalDate, Integer> focusSession = focusSessions.stream()
                .collect(Collectors.groupingBy(
                        session -> session.getStartedAt().toLocalDate(),
                        Collectors.summingInt(FocusSession::getActualTime)
                ));

        List<WeeklyStatDto> weeklyStatDto = new ArrayList<>();

        for (LocalDate date = weekStart; !date.isAfter(today); date = date.plusDays(1)) {
            weeklyStatDto.add(new WeeklyStatDto(
                    date,
                    screenTime.getOrDefault(date, 0),
                    focusSession.getOrDefault(date, 0)
            ));
        }

        return weeklyStatDto;
    }
}

