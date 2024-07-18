package org.eternity.reservation.domain;

import org.eternity.generic.Money;

import java.time.LocalDateTime;

public class Screening {
    private Long id;
    private Movie movie;
    private int sequence;
    private LocalDateTime whenScreened;

    public Screening(Long id, Movie movie, int sequence, LocalDateTime whenScreened) {
        this.id = id;
        this.movie = movie;
        this.sequence = sequence;
        this.whenScreened = whenScreened;
    }

    public Screening(Movie movie, int sequence, LocalDateTime whenScreened) {
        this(null, movie, sequence, whenScreened);
    }

    public Long getId() {
        return id;
    }

    public Movie getMovie() {
        return movie;
    }

    public Reservation reserve(Customer customer, int audienceCount) {
        Money fee = movie.calculateFee(this).times(audienceCount);
        return new Reservation(customer, this, audienceCount, fee);
    }

    public Money getFixedFee() {
        return movie.getFee();
    }

    public boolean isSequence(int sequence) {
        return this.sequence == sequence;
    }

    public LocalDateTime getStartTime() {
        return whenScreened;
    }
}
