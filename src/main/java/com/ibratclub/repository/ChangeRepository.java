package com.ibratclub.repository;

import com.ibratclub.model.Change;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChangeRepository extends JpaRepository<Change, Long> {
}
