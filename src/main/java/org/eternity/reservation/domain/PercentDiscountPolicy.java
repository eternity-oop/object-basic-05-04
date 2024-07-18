package org.eternity.reservation.domain;

import org.eternity.generic.Money;

public class PercentDiscountPolicy extends DiscountPolicy {
    private double percent;

    public PercentDiscountPolicy(Long id, double percent, DiscountCondition... conditions) {
        super(id, conditions);
        this.percent = percent;
    }

    public PercentDiscountPolicy(double percent, DiscountCondition... conditions) {
        this(null , percent, conditions);
    }

    @Override
    protected Money getDiscountAmount(Screening screening) {
        return screening.getFixedFee().times(percent);
    }
}
