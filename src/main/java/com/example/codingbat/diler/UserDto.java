package com.example.codingbat.diler;

import com.example.codingbat.entity.Account;
import com.example.codingbat.entity.Answer;
import com.example.codingbat.entity.Language;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @NotNull(message = "account shouldn't be empty")
    private Integer account;
    @NotNull(message = "answer shouldn't be empty")
    private Integer answer;
    @NotNull(message = "language shouldn't be empty")
    private Integer language;
}
