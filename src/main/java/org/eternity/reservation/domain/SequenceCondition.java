package org.eternity.reservation.domain;

public class SequenceCondition implements DiscountCondition {
    private Long id;
    private int sequence;

    public SequenceCondition(Long id, int sequence) {
        this.id = id;
        this.sequence = sequence;
    }

    public SequenceCondition(int sequence) {
        this(null, sequence);
    }


    @Override
    public boolean isSatisfiedBy(Screening screening) {
        return screening.isSequence(sequence);
    }
}
