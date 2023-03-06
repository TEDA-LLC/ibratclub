package com.ibratclub.repository;

import com.ibratclub.model.Company;
import com.ibratclub.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Malikov Azizjon  *  16.01.2023  *  18:36   *  IbratClub
 */
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Page<Company> findAllByActive(boolean active, Pageable pageable);
    Optional<Company> findByINN(String inn);
    List<Company> findAllByDirector(Employee employee);

}
