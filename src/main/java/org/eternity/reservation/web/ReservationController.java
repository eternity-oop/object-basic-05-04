package org.eternity.reservation.web;

import org.eternity.generic.Money;
import org.eternity.reservation.domain.Reservation;
import org.eternity.reservation.service.CustomerDAO;
import org.eternity.reservation.service.ReservationDAO;
import org.eternity.reservation.service.ReservationService;
import org.eternity.reservation.service.ScreeningDAO;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class ReservationController {
    private TransactionTemplate transactionTemplate;
    private CustomerDAO customerDAO;
    private ScreeningDAO screeningDAO;
    private ReservationDAO reservationDAO;

    private ReservationService reservationService;

    public ReservationController(TransactionTemplate transactionTemplate,
                                 CustomerDAO customerDAO,
                                 ScreeningDAO screeningDAO,
                                 ReservationDAO reservationDAO,
                                 ReservationService reservationService) {
        this.transactionTemplate = transactionTemplate;
        this.customerDAO = customerDAO;
        this.screeningDAO = screeningDAO;
        this.reservationDAO = reservationDAO;
        this.reservationService = reservationService;
    }

    @PostMapping("/reservations")
    public ReservationResponse reserve(@RequestBody ReservationRequest request) {
        Reservation result = reservationService.reserveScreening(
                                request.getCustomerId(),
                                request.getScreeningId(),
                                request.getAudienceCount());

        return new ReservationResponse(result);
    }

    // @PostMapping("/reservations")
    // public ReservationResponse reserve(@RequestBody ReservationRequest request) {
    //     Reservation result = transactionTemplate.execute((status) -> {
    //         Customer customer = customerDAO.find(request.getCustomerId());
    //         Screening screening = screeningDAO.find(request.getScreeningId());
    //
    //         Reservation reservation = screening.reserve(customer, request.getAudienceCount());
    //
    //         reservationDAO.save(reservation);
    //
    //         return reservation;
    //     });
    //
    //     return new ReservationResponse(result);
    // }

}

class ReservationRequest {
    private Long customerId;
    private Long screeningId;
    private Integer audienceCount;

    public Long getCustomerId() {
        return customerId;
    }

    public Long getScreeningId() {
        return screeningId;
    }

    public Integer getAudienceCount() {
        return audienceCount;
    }
}

class ReservationResponse {
    private Long customerId;
    private String customerName;
    private Long screeningId;
    private Long movieId;
    private String movieTitle;
    private Integer audienceCount;
    private Money fee;
    private LocalDateTime whenScreened;

    public ReservationResponse(Reservation reservation) {
        this.customerId = reservation.getCustomer().getId();
        this.customerName = reservation.getCustomer().getName();
        this.screeningId = reservation.getScreening().getId();
        this.movieId = reservation.getScreening().getMovie().getId();
        this.movieTitle = reservation.getScreening().getMovie().getTitle();
        this.whenScreened = reservation.getScreening().getStartTime();
        this.audienceCount = reservation.getAudienceCount();
        this.fee = reservation.getFee();
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Long getScreeningId() {
        return screeningId;
    }

    public Long getMovieId() {
        return movieId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public Integer getAudienceCount() {
        return audienceCount;
    }

    public Money getFee() {
        return fee;
    }

    public LocalDateTime getWhenScreened() {
        return whenScreened;
    }
}