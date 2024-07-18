package org.eternity.reservation.domain;

import org.eternity.generic.Money;

public class Reservation {
    private Long id;
    private Customer customer;
    private Screening screening;
    private int audienceCount;
    private Money fee;

    public Reservation(Customer customer, Screening screening, int audienceCount, Money fee) {

        this.customer = customer;
        this.screening = screening;
        this.audienceCount = audienceCount;
        this.fee = fee;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Screening getScreening() {
        return screening;
    }

    public int getAudienceCount() {
        return audienceCount;
    }

    public Money getFee() {
        return fee;
    }
}
