package com.online.test.onlinetest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NewQuestionDTO {
    private String name;
    private String description;
    private short score;
}
