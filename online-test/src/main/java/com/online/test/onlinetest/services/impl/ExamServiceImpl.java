package com.online.test.onlinetest.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.online.test.onlinetest.dto.ExamDTO;
import com.online.test.onlinetest.dto.NewExamDTO;
import com.online.test.onlinetest.exceptions.ResourceNotFoundException;
import com.online.test.onlinetest.models.Exam;
import com.online.test.onlinetest.repositories.ExamRepository;
import com.online.test.onlinetest.services.ExamService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExamServiceImpl implements ExamService {

    final ModelMapper modelMapper;
    final ExamRepository examRepository;

    @Autowired
    public ExamServiceImpl(@Autowired ExamRepository repository, ModelMapper mapper){
        this.examRepository = repository;
        this.modelMapper = mapper;
    }

    @Override
    @Transactional
    public ExamDTO create(NewExamDTO examDTO) {
        Exam exam = modelMapper.map(examDTO, Exam.class);
        examRepository.save(exam);        
        return modelMapper.map(exam, ExamDTO.class); 
    }

    @Override
    @Transactional(readOnly = true)
    public ExamDTO retrieve(Long id) {
        Exam exam = examRepository.findById(id)
            .orElseThrow(()-> new ResourceNotFoundException("Exam not found"));
        return modelMapper.map(exam, ExamDTO.class);
    }

    @Override
    @Transactional
    public ExamDTO update(ExamDTO examDTO, Long id) {
        Exam exam = examRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Exam not found"));
        
        exam.setId(id);
        exam = modelMapper.map(examDTO, Exam.class);
        examRepository.save(exam);       

        return modelMapper.map(exam, ExamDTO.class);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Exam exam = examRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Exam not found"));        
        examRepository.deleteById(exam.getId());        
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExamDTO> list() {
        List<Exam> exams = examRepository.findAll();
        return exams.stream().map(exam -> modelMapper.map(exam, ExamDTO.class))
            .collect(Collectors.toList());        
    }
    
}