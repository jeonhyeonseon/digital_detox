package com.digitaldetox.digital_detox.dashboard.dto;

import com.digitaldetox.digital_detox.badge.domain.BadgeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NextBadgeDto {

    private BadgeType badgeType;
    private int remainingDays;
}
