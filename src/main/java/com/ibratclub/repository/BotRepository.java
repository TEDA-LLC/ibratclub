package com.ibratclub.repository;

import com.ibratclub.model.Bot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Malikov Azizjon  *  16.01.2023  *  23:37   *  IbratClub
 */

@Repository
public interface BotRepository extends JpaRepository<Bot, Long> {
}
