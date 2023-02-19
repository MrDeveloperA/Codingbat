package com.example.codingbat.diler;

import com.example.codingbat.entity.*;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MainPageDto {
    private String nameSite;
    private String notification;
    private Integer help;
    private Integer about;
    private Integer codeHelpVideo;
    private Integer done;
    private Integer account;
    private Integer programmingLanguage;
    private Integer outerTheme;
    private Integer miscCodePractice;
    private Integer privacy;
}
