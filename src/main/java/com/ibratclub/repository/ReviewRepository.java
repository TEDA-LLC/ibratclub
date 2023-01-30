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

    List<Review> findAllByConfirmationTrueAndUser_Bot_Id(Long botId);
    @Query(value = """
            SELECT r.id as id, confirmation, date_time as dataTime, text, user_id as "user"
            FROM review r
                     inner join users u on u.id = r.user_id
            where r.confirmation = true
              and u.company_id = ?1
            ORDER BY date_time DESC
            LIMIT 10""", nativeQuery = true)
    List<Review> findAllByConfirmationTrueForUsers(Long companyId);
    List<Review> findAllByConfirmationFalseAndUser_Bot_Id(Long botId);

}
