package com.ibratclub.repository;

import com.ibratclub.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Malikov Azizjon  *  23.01.2023  *  23:35   *  IbratClub
 */
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
