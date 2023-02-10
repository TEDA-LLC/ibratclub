package com.ibratclub.repository;

import com.ibratclub.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Malikov Azizjon  *  15.01.2023  *  21:47   *  IbratClub
 */
@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
}
