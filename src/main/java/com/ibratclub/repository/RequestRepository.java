package com.ibratclub.repository;

import com.ibratclub.model.Product;
import com.ibratclub.model.Request;
import com.ibratclub.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Malikov Azizjon  *  15.01.2023  *  21:49   *  IbratClub
 */
@Repository
public interface RequestRepository extends JpaRepository<Request,Long> {

    List<Request> findAllByUserAndProduct_Category_Department_Id(User user, Long botId, Sort sort);
    List<Request> findAllByUser(User user);
    List<Request> findAllByProduct_Category_Department_Id(Long botId);
    List<Request> findAllByProductAndUser_Email(Product product, String email);
    List<Request> findAllByProductAndUser_Phone(Product product, String phone);
    List<Request> findAllByProductAndUser(Product product, User user);
    Page<Request> findAllByView(boolean view, Pageable pageable);
    List<Request> findAllByProduct_Id(Long productId);
}
