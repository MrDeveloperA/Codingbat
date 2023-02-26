package com.example.codingbat.diler;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlockDto {
    @NotNull(message = "name shouldn't be empty")
    private String name;
    private Integer numberStar;
    @NotNull(message = "declare shouldn't be empty")
    private Integer declare;
    @NotNull(message = "theme shouldn't be empty")
    private Integer theme;
}
