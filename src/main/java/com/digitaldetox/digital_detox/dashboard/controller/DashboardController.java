package com.digitaldetox.digital_detox.dashboard.controller;

import com.digitaldetox.digital_detox.auth.service.CustomUserDetails;
import com.digitaldetox.digital_detox.dashboard.dto.DashboardResponseDto;
import com.digitaldetox.digital_detox.dashboard.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    public DashboardResponseDto getDashboard(@AuthenticationPrincipal CustomUserDetails customUserDetails) {

        Long memberId = customUserDetails.getMemberId();
        return dashboardService.getDashboard(memberId);
    }
}
