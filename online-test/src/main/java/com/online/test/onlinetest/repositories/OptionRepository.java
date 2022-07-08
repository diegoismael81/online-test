package com.online.test.onlinetest.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.online.test.onlinetest.models.Option;
import com.online.test.onlinetest.models.Question;

public interface OptionRepository extends JpaRepository<Option, Long> {

    public List<Option> findByQuestion(Question question);
    
}
