package com.online.test.onlinetest.services;

import java.util.List;

import com.online.test.onlinetest.dto.ExamDTO;
import com.online.test.onlinetest.dto.NewExamDTO;

public interface ExamService {
    
    public ExamDTO create(NewExamDTO examDTO);
    public ExamDTO retrieve(Long id) throws Exception;
    public ExamDTO update(ExamDTO examDTO, Long id) throws Exception;
    public void delete(Long id) throws Exception;

    public List<ExamDTO> list();
}
