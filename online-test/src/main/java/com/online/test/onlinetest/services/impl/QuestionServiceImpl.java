package com.online.test.onlinetest.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.online.test.onlinetest.dto.NewQuestionDTO;
import com.online.test.onlinetest.dto.QuestionDTO;
import com.online.test.onlinetest.exceptions.ResourceNotFoundException;
import com.online.test.onlinetest.models.Exam;
import com.online.test.onlinetest.models.Question;
import com.online.test.onlinetest.repositories.ExamRepository;
import com.online.test.onlinetest.repositories.QuestionRepository;
import com.online.test.onlinetest.services.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

    final ModelMapper modelMapper;
    final QuestionRepository repository;
    final ExamRepository examRepository;

    public QuestionServiceImpl(QuestionRepository r, ModelMapper m, ExamRepository er)
    {
        this.modelMapper = m;
        this.repository = r;
        this.examRepository = er;
    }


    @Override
    public void create(NewQuestionDTO questionDTO) {
        Question question = modelMapper.map(questionDTO, Question.class);    
        repository.save(question);
    }


    @Override
    public List<QuestionDTO> list(Long idExam) {
        Exam exam = examRepository.findById(idExam)
            .orElseThrow(()-> new ResourceNotFoundException("Exam not found"));
        List<Question> questions = repository.findByExam(exam);
        //Lambda
        return questions.stream().map(q -> modelMapper.map(q, QuestionDTO.class) )
            .collect(Collectors.toList());
    }
    
}
