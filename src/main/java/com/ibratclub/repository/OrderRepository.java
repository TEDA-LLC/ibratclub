package com.ibratclub.repository;

import com.ibratclub.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Malikov Azizjon  *  16.01.2023  *  23:40   *  IbratClub
 */

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Page<Order> findAllByActiveTrueAndReadyTrue(Pageable pageable);
    Page<Order> findAllByActiveTrueAndReadyFalse(Pageable pageable);
    Page<Order> findAllByActiveFalseAndReadyTrue(Pageable pageable);
    Page<Order> findAllByActiveFalseAndReadyFalse(Pageable pageable);

}
