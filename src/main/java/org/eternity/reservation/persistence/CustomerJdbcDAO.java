package org.eternity.reservation.persistence;

import org.eternity.reservation.domain.Customer;
import org.eternity.reservation.service.CustomerDAO;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerJdbcDAO implements CustomerDAO {
    private JdbcClient jdbcClient;

    public CustomerJdbcDAO(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Customer find(Long id) {
        return jdbcClient.sql("SELECT ID, NAME FROM CUSTOMER WHERE ID = :id")
                .param("id", id)
                .query((rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("NAME")))
                .single();
    }
}
