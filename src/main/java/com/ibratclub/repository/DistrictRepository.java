package com.ibratclub.repository;

import com.ibratclub.model.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Malikov Azizjon  *  15.01.2023  *  22:01   *  IbratClub
 */
@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {
    List<District> findAllByRegion_Id(Long regionId);
    Optional<District> findByName(String name);
}
