package com.example.codingbat.diler;

import com.example.codingbat.entity.*;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThemeDto {
    @NotNull(message = "name shouldn't be empty")
    private String name;
    @NotNull(message = "task text shouldn't be empty")
    private Integer taskText;
    @NotNull(message = "task shouldn't be empty")
    private Integer task;
    @NotNull(message = "solution shouldn't be empty")
    private Integer solution;
    @NotNull(message = "given shouldn't be empty")
    private Integer givenCode;
    @NotNull(message = "answer shouldn't be empty")
    private Integer answer;

}
