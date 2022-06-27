package com.online.test.onlinetest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExamListDTO {
    private Long id;
    private String title;
    private String description;
    private short timeLimit;   
    private short numberOfQuestions;  
}
