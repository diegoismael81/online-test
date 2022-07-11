package com.online.test.onlinetest.services;

import java.util.List;

import com.online.test.onlinetest.dto.ExamDTO;
import com.online.test.onlinetest.dto.ExamListDTO;
import com.online.test.onlinetest.dto.NewExamDTO;

public interface ExamService {
    
    public ExamDTO create(NewExamDTO examDTO);
    public ExamDTO retrieve(Long id);
    public ExamDTO update(ExamDTO examDTO, Long id);
    public void delete(Long id);
    public long count();

    public List<ExamListDTO> list(int page, int size, String sort);
}
