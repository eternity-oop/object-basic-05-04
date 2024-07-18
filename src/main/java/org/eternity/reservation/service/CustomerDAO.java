package org.eternity.reservation.service;

import org.eternity.reservation.domain.Customer;

public interface CustomerDAO {
    Customer find(Long customerId);
}
