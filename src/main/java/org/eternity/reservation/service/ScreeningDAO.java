package org.eternity.reservation.service;

import org.eternity.reservation.domain.Screening;

public interface ScreeningDAO {
    Screening find(Long screeningId);
}
