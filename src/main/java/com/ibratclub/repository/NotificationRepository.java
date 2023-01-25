package com.ibratclub.repository;

import com.ibratclub.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Malikov Azizjon  *  25.01.2023  *  12:40   *  IbratClub
 */

@Repository
public interface NotificationRepository extends JpaRepository <Notification, Long>{
}
