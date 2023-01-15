package com.ibratclub.repository;

import com.ibratclub.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Malikov Azizjon  *  15.01.2023  *  22:01   *  IbratClub
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}
