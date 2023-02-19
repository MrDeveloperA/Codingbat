package com.example.codingbat.diler;

import com.example.codingbat.entity.InnerTheme;
import com.example.codingbat.entity.InnerThemeWithVideo;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CodeHelpVideoDto {
    @NotNull(message = "Inner theme shouldn't be empty")
    private Integer innerTheme;
    @NotNull(message = "Inner theme video theme shouldn't be empty")
    private Integer innerThemeWithVideo;
}
