package com.online.test.onlinetest.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.online.test.onlinetest.dto.NewQuestionDTO;
import com.online.test.onlinetest.dto.QuestionDTO;
import com.online.test.onlinetest.dto.QuestionExamDTO;
import com.online.test.onlinetest.exceptions.NoContentException;
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

    public QuestionServiceImpl(QuestionRepository r, ExamRepository er, ModelMapper m)
    {
        this.modelMapper = m;
        this.repository = r;
        this.examRepository = er;
    }


    @Override
    @Transactional
    public QuestionDTO create(Long idExam, NewQuestionDTO questionDTO) {
        Exam exam = examRepository.findById(idExam)
            .orElseThrow(()-> new ResourceNotFoundException("Exam not found"));
        Question question = modelMapper.map(questionDTO, Question.class);    
        question.setExam(exam);
        repository.save(question);
        return modelMapper.map(question, QuestionDTO.class); 
    }

    @Override
    @Transactional(readOnly=true)
    public QuestionExamDTO retrieve(Long idExam, Long id) {
        Exam exam = examRepository.findById(idExam)
            .orElseThrow(()-> new ResourceNotFoundException("Exam not found"));
        Question question = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Question not found"));
        question.setExam(exam);
        return modelMapper.map(question, QuestionExamDTO.class);
    }

    @Override
    @Transactional
    public QuestionExamDTO update(QuestionDTO questionDTO, Long idExam, Long id) {
        Exam exam = examRepository.findById(idExam)
        .orElseThrow(()-> new ResourceNotFoundException("Exam not found"));
        Question question = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Question not found"));
        question = modelMapper.map(questionDTO, Question.class);
        question.setExam(exam);
        repository.save(question);       
        return modelMapper.map(question, QuestionExamDTO.class);
    }


    @Override
    @Transactional
    public void delete(Long idExam, Long id) {
        Exam exam = examRepository.findById(idExam)
        .orElseThrow(()-> new ResourceNotFoundException("Exam not found"));
        Question question = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Question not found"));
        question.setExam(exam);
        repository.deleteById(question.getId());  
    }

    @Override
    @Transactional(readOnly=true)
    public List<QuestionDTO> list(Long idExam) {
        Exam exam = examRepository.findById(idExam)
            .orElseThrow(()-> new ResourceNotFoundException("Exam not found"));
        List<Question> questions = repository.findByExam(exam);
        if(questions.isEmpty()) throw new NoContentException("Questions is empty");
        //Lambda ->
        return questions.stream().map(q -> modelMapper.map(q, QuestionDTO.class) )
            .collect(Collectors.toList());
    }


    
    
}
