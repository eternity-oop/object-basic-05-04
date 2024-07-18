package org.eternity.reservation.service;

import org.eternity.reservation.domain.Customer;
import org.eternity.reservation.domain.Reservation;
import org.eternity.reservation.domain.Screening;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

@Service
public class ReservationService {
    private TransactionTemplate transactionTemplate;
    private CustomerDAO customerDAO;
    private ScreeningDAO screeningDAO;
    private ReservationDAO reservationDAO;

    public ReservationService(TransactionTemplate transactionTemplate, CustomerDAO customerDAO, ScreeningDAO screeningDAO, ReservationDAO reservationDAO) {
        this.transactionTemplate = transactionTemplate;
        this.customerDAO = customerDAO;
        this.screeningDAO = screeningDAO;
        this.reservationDAO = reservationDAO;
    }

    public Reservation reserveScreening(Long customerId, Long screeningId, Integer audienceCount) {
        return transactionTemplate.execute((status) -> {
            Customer customer = customerDAO.find(customerId);
            Screening screening = screeningDAO.find(screeningId);

            Reservation reservation = screening.reserve(customer, audienceCount);

            reservationDAO.save(reservation);

            return reservation;
        });
    }
}
