package com.ibratclub.repository;

import com.ibratclub.model.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Malikov Azizjon  *  16.01.2023  *  18:13   *  IbratClub
 */
@Repository
public interface AvatarRepository extends JpaRepository<Avatar, Long> {
}
