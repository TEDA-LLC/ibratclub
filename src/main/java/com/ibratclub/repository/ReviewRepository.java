package com.ibratclub.repository;

import com.ibratclub.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Malikov Azizjon  *  16.01.2023  *  17:32   *  IbratClub
 */
@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {

    List<Review> findAllByConfirmationTrue();
    @Query(value = "SELECT * FROM review r where r.confirmation = true ORDER BY date_time DESC LIMIT 10", nativeQuery = true)
    List<Review> findAllByConfirmationTrueForUsers();
    List<Review> findAllByConfirmationFalse();

}
