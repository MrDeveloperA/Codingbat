package com.example.codingbat.diler;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LearnThemeDto {
    @NotNull(message = "name shouldn't be empty")
    private String name;
    @NotNull(message = "attachment shouldn't be empty")
    private Integer attachment;
    @NotNull(message = "description shouldn't be empty")
    private Integer description;
}
