package com.ibratclub.repository;

import com.ibratclub.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Malikov Azizjon  *  16.01.2023  *  18:32   *  IbratClub
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByPhoneFirst(String phoneFirst);
    Page<Employee> findAllByActiveTrue(Pageable pageable);
    Page<Employee> findAllByActiveFalse(Pageable pageable);
    Page<Employee> findAllByActiveTrueAndCompany_Id(Long companyId, Pageable pageable);
    Page<Employee> findAllByActiveFalseAndCompany_Id(Long companyId, Pageable pageable);

    Page<Employee> findAllByCompany_Id(Long companyId, Pageable pageable);

}
