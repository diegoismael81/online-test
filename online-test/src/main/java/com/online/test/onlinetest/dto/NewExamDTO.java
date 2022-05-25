package com.online.test.onlinetest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NewExamDTO {
    private String title;
    private String description;
    private short timeLimit;
    private double minimumPassingScore;
    private short numberOfQuestions;
    private String instructions;      
}
