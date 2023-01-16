package com.ibratclub.repository;

import com.ibratclub.model.WordHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Malikov Azizjon  *  16.01.2023  *  18:42   *  IbratClub
 */
@Repository
public interface WordHistoryRepository extends JpaRepository<WordHistory, Long> {
}
