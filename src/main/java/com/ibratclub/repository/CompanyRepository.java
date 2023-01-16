package com.ibratclub.repository;

import com.ibratclub.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Malikov Azizjon  *  16.01.2023  *  18:36   *  IbratClub
 */
@Repository
public interface CompanyRepository extends JpaRepository<Employee, Long> {
}
