package com.example.codingbat.diler;

import com.example.codingbat.entity.*;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThemeToPracticeDto {
    private Integer problemText;
    private Integer condition;
    private Integer solution;
    private Integer givenCode;
    private Integer editCodeFromUser;
    private Integer fontSize;
    private boolean shorterOutput;
}
