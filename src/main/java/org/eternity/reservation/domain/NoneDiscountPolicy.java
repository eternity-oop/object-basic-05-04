package org.eternity.reservation.domain;

import org.eternity.generic.Money;

public class NoneDiscountPolicy extends DiscountPolicy {

    public NoneDiscountPolicy() {
        super(null, screening -> true);
    }

    @Override
    protected Money getDiscountAmount(Screening screening) {
        return Money.ZERO;
    }
}
