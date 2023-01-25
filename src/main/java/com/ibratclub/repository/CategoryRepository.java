package com.ibratclub.repository;

import com.ibratclub.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * @author Malikov Azizjon  *  16.01.2023  *  17:51   *  IbratClub
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {

//    Optional<Category> findByNameUzOrNameRuOrNameEn(String nameUz, String nameRu, String nameEn);

}
