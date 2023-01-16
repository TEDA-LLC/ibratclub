package com.ibratclub.repository;

import com.ibratclub.model.ReviewCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Malikov Azizjon  *  16.01.2023  *  17:33   *  IbratClub
 */
public interface ReviewCategoryRepository extends JpaRepository<ReviewCategory, Long> {

    List<ReviewCategory> findAllByActiveTrue();
    Optional<ReviewCategory> findByName(String name);

}
