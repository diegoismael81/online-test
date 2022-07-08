package com.online.test.onlinetest.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.online.test.onlinetest.dto.NewOptionDTO;
import com.online.test.onlinetest.dto.OptionDTO;
import com.online.test.onlinetest.exceptions.NoContentException;
import com.online.test.onlinetest.exceptions.ResourceNotFoundException;
import com.online.test.onlinetest.models.Exam;
import com.online.test.onlinetest.models.Option;
import com.online.test.onlinetest.models.Question;
import com.online.test.onlinetest.repositories.ExamRepository;
import com.online.test.onlinetest.repositories.OptionRepository;
import com.online.test.onlinetest.repositories.QuestionRepository;
import com.online.test.onlinetest.services.OptionService;

@Service
public class OptionServiceImpl implements OptionService {

    final ModelMapper modelMapper;
    final OptionRepository repository;
    final QuestionRepository questionRepository;
    final ExamRepository examRepository;

    public OptionServiceImpl(OptionRepository r, QuestionRepository qr, ExamRepository er, ModelMapper m)
    {
        this.modelMapper = m;
        this.repository = r;
        this.examRepository = er;
        this.questionRepository = qr;
    }

    @Override
    @Transactional
    public List<OptionDTO> create(Long idExam, Long idQuestion, List<NewOptionDTO> options) {
        Exam exam = examRepository.findById(idExam).orElseThrow(()-> new ResourceNotFoundException("Exam not found"));
        Question question = questionRepository.findById(idQuestion).orElseThrow(()-> new ResourceNotFoundException("Question not found"));
        question.setExam(exam);
        List<OptionDTO> result = new ArrayList<OptionDTO>();
        for(NewOptionDTO op : options){
            Option option = modelMapper.map(op, Option.class);
            option.setQuestion(question);
            repository.save(option);
            result.add(modelMapper.map(option, OptionDTO.class));
        }
        /*options.forEach(op -> {
        });        */
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<OptionDTO> list(Long idExam, Long idQuestion) {
        Exam exam = examRepository.findById(idExam).orElseThrow(()-> new ResourceNotFoundException("Exam not found"));
        Question question = questionRepository.findById(idQuestion).orElseThrow(()-> new ResourceNotFoundException("Question not found"));
        question.setExam(exam);
        if(question.getOptions().isEmpty()) throw new NoContentException("Options is empty");
        return question.getOptions().stream().map(op -> modelMapper.map(op, OptionDTO.class))
        .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void remove(Long idExam, Long idQuestion) {
        Exam exam = examRepository.findById(idExam).orElseThrow(()-> new ResourceNotFoundException("Exam not found"));
        Question question = questionRepository.findById(idQuestion).orElseThrow(()-> new ResourceNotFoundException("Question not found"));
        question.setExam(exam);
        if(question.getOptions().isEmpty()) throw new NoContentException("Options is empty");
        question.getOptions().forEach(op -> {
            repository.delete(op);            
        });                      
    }
    
}
