package com.example.codingbat.diler;

import com.example.codingbat.entity.ThemeToPractice;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OuterThemeDto {
    private String outerTheme;
    private Integer numberStar;
    private Integer themeToPractice;
    private boolean tick;
}
