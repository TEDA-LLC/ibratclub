package com.ibratclub.repository;

import com.ibratclub.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    Page<Message> findAllByFlagAndEmployeePhone(int flag, String phone, Pageable pageable);
    List<Message> findAllByFlagAndEmployeePhone(int flag, String phone);
    Page<Message> findAllByEmployeePhone(String phone, Pageable pageable);
}
