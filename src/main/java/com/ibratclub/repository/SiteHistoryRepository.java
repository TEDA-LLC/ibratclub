package com.ibratclub.repository;

import com.ibratclub.model.SiteHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Malikov Azizjon  *  16.01.2023  *  18:04   *  IbratClub
 */
@Repository
public interface SiteHistoryRepository extends JpaRepository<SiteHistory, Long> {
    List<SiteHistory> findAllByUser_Company_Id(Long companyId);
}
