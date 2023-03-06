package com.ibratclub.repository;

import com.ibratclub.model.WorkCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkCategoryRepository extends JpaRepository<WorkCategory, Long> {
    Optional<WorkCategory> findByName(String name);
}
