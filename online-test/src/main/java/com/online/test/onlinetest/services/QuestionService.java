package com.online.test.onlinetest.services;

import java.util.List;

import com.online.test.onlinetest.dto.NewQuestionDTO;
import com.online.test.onlinetest.dto.QuestionDTO;
import com.online.test.onlinetest.dto.QuestionExamDTO;

public interface QuestionService {
    public QuestionDTO create(Long idExam, NewQuestionDTO questionDTO);
    public QuestionExamDTO retrieve(Long idExam, Long id);
    public QuestionExamDTO update(QuestionDTO questionDTO, Long idExam, Long id);
    public void delete(Long idExam, Long id);

    public List<QuestionDTO> list(Long idExam);
}
