package com.ibratclub.repository;

import com.ibratclub.model.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Malikov Azizjon  *  16.01.2023  *  18:45   *  IbratClub
 */
@Repository
public interface UserHistoryRepository extends JpaRepository<UserHistory, Long> {

    @Query(value = "select count(id) as amount from user_history where product_id =:id",nativeQuery = true)
    long getAmountByProduct(@Param("id") long id);

}
