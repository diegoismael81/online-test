package com.online.test.onlinetest.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.online.test.onlinetest.models.Exam;
import com.online.test.onlinetest.models.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    public List<Question> findByExam(Exam exam);
    
}
