package org.eternity.reservation.domain;

import org.eternity.generic.Money;

import java.util.List;

public abstract class DiscountPolicy {
    private Long id;
    private List<DiscountCondition> conditions;

    public DiscountPolicy(Long id, DiscountCondition ... conditions) {
        this.id = id;
        this.conditions = List.of(conditions);
    }

    public Money calculateDiscount(Screening screening) {
        for (DiscountCondition each : conditions) {
            if (each.isSatisfiedBy(screening)) {
                return getDiscountAmount(screening);
            }
        }

        return Money.ZERO;
    }

    abstract protected Money getDiscountAmount(Screening screening);
}
