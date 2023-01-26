package com.ibratclub.repository;

import com.ibratclub.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Malikov Azizjon  *  16.01.2023  *  17:44   *  IbratClub
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByCategoryId(Long id);

    List<Product> findAllByActiveTrueAndCategory_Bot_Id(Long botId);

}
