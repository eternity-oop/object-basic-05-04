package org.eternity.reservation.service;

import org.eternity.reservation.domain.Reservation;

public interface ReservationDAO {
    void save(Reservation reservation);
}
