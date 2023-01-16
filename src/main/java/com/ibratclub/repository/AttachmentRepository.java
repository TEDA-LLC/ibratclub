package com.ibratclub.repository;

import com.ibratclub.model.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Malikov Azizjon  *  16.01.2023  *  17:58   *  IbratClub
 */
@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
}
