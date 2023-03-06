package com.ibratclub.repository;

import com.ibratclub.model.WorkType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkTypeRepository extends JpaRepository<WorkType, Long> {
    Optional<WorkType> findByName(String name);
}
