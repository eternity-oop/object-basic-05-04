package org.eternity.reservation.domain;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class PeriodCondition implements DiscountCondition {
    private Long id;
    private DayOfWeek dayOfWeek;
    private LocalTime startTime, endTime;

    public PeriodCondition(Long id, DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
        this.id = id;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public PeriodCondition(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
        this(null, dayOfWeek, startTime, endTime);
    }

    public boolean isSatisfiedBy(Screening screening) {
        return  screening.getStartTime()
                .getDayOfWeek().equals(dayOfWeek) &&
                startTime.compareTo(screening.getStartTime().toLocalTime()) <= 0 &&
                endTime.compareTo( screening.getStartTime()
                        .toLocalTime()) >= 0;
    }
}
