package com.online.test.onlinetest.services;

import java.util.List;

import com.online.test.onlinetest.dto.NewQuestionDTO;
import com.online.test.onlinetest.dto.QuestionDTO;

public interface QuestionService {
    public void create(NewQuestionDTO questionDTO);
    public List<QuestionDTO> list(Long idExam);
}
