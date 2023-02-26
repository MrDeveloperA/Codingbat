package com.example.codingbat.diler;

import com.example.codingbat.entity.Account;
import com.example.codingbat.entity.LearnThemes;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebSiteDto {
    @NotNull(message = "name shouldn't be empty")
    private String name;
    @NotNull(message = "learnTheme shouldn't be empty")
    private Integer learnThemes;
    @NotNull(message = "account shouldn't be empty")
    private Integer account;
}
