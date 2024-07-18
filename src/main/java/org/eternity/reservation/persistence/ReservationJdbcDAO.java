package org.eternity.reservation.persistence;

import org.eternity.reservation.domain.Reservation;
import org.eternity.reservation.service.ReservationDAO;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class ReservationJdbcDAO implements ReservationDAO {
    private JdbcClient jdbcClient;

    public ReservationJdbcDAO(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public void save(Reservation reservation) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcClient.sql("INSERT INTO RESERVATION(CUSTOMER_ID, SCREENING_ID, AUDIENCE_COUNT, FEE) " +
                        "VALUES(:customerId, :screeningId, :audienceCount, :fee)")
                .param("customerId", reservation.getCustomer().getId())
                .param("screeningId", reservation.getScreening().getId())
                .param("audienceCount", reservation.getAudienceCount())
                .param("fee", reservation.getFee().longValue())
                .update(keyHolder);

        reservation.setId(keyHolder.getKey().longValue());
    }
}
