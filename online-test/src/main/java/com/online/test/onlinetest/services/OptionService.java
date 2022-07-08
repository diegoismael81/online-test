package com.online.test.onlinetest.services;

import java.util.List;

import com.online.test.onlinetest.dto.NewOptionDTO;
import com.online.test.onlinetest.dto.OptionDTO;

public interface OptionService {
    public List<OptionDTO> create(Long idExam, Long idQuestion, List<NewOptionDTO> list);
    public List<OptionDTO> list(Long idExam, Long idQuestion);
    public void remove(Long idExam, Long idQuestion);
}
