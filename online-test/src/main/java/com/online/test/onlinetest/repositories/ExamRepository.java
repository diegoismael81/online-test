package com.online.test.onlinetest.repositories;

import com.online.test.onlinetest.models.Exam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
    
}
