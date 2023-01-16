package com.ibratclub.repository;

import com.ibratclub.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Malikov Azizjon  *  16.01.2023  *  23:38   *  IbratClub
 */

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
}
