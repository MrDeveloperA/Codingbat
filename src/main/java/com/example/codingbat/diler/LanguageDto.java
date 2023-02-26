package com.example.codingbat.diler;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LanguageDto {
    @NotNull(message = "language shouldn't be empty")
    private String name;
    @NotNull(message = "block shouldn't be empty")
    private Integer block;
}
