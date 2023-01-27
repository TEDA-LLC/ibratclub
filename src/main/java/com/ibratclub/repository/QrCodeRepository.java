//package com.ibratclub.repository;
//
//import com.ibratclub.model.Product;
//import com.ibratclub.model.QrCode;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//@Repository
//public interface QrCodeRepository extends JpaRepository<QrCode, UUID> {
//    List<QrCode> findAllByProduct(Product product);
//
//    Optional<QrCode> findByRequest_Id(Long id);
//}