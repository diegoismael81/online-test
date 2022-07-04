package com.online.test.onlinetest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class QuestionDTO {
    private Long id;
    private String name;
    private String description;
    private short score;
}
