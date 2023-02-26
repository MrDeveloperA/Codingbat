package com.example.codingbat.diler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDto {
    private String answerCode;
    private Integer theme;
    private Integer user;

}
