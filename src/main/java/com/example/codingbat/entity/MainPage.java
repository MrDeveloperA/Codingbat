package com.example.codingbat.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class MainPage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nameSite;
    private String notification;
    @OneToOne
    private Help help;
    @OneToOne
    private About about;
    @ManyToOne
    private CodeHelpVideo codeHelpVideo;
    @OneToOne
    private Done done;
    @ManyToOne
    private Account account;
    @ManyToOne
    private ProgrammingLanguage programmingLanguage;
    @ManyToOne
    private OuterTheme outerTheme;
    @ManyToOne
    private MiscCodePractice miscCodePractice;
    @OneToOne
    private Privacy privacy;
}
