package org.eternity.reservation.domain;

public interface DiscountCondition {
    boolean isSatisfiedBy(Screening screening);
}
