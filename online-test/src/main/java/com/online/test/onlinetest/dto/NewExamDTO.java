package com.online.test.onlinetest.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NewExamDTO {
    @NotNull(message = "Title width can't be null.")
    private String title;
    @NotNull(message = "Description width can't be null.")
    private String description;
    @Min(message = "Time limit can't be lower 60 minutes", value = 60)
    @Max(message = "Time limit can't be more 180 minutes",value = 180)
    private short timeLimit;   
    private double minimumPassingScore;   
    private short numberOfQuestions;    
    private String instructions;      
}
