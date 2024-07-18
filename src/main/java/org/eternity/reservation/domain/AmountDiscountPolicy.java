package org.eternity.reservation.domain;

import org.eternity.generic.Money;

public class AmountDiscountPolicy extends DiscountPolicy {
    private Money discountAmount;

    public AmountDiscountPolicy(Long id, Money discountAmount, DiscountCondition ... conditions) {
        super(id, conditions);
        this.discountAmount = discountAmount;
    }

    public AmountDiscountPolicy(Money discountAmount, DiscountCondition ... conditions) {
        this(null, discountAmount, conditions);
    }

    @Override
    protected Money getDiscountAmount(Screening screening) {
        return discountAmount;
    }
}
