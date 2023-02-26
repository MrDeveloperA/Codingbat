package com.example.codingbat.diler;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SolutionDto {
    @NotNull(message = "given code shouldn't be empty")
    private Integer givenCode;
    @NotNull(message = "right answer shouldn't be empty")
    private Integer rightAnswer;
}
