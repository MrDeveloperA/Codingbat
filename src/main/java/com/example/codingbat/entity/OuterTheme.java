package com.example.codingbat.entity;

import ch.qos.logback.core.joran.conditional.ThenAction;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class OuterTheme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String outerTheme;
    private Integer numberStar;
    @ManyToOne
    private ThemeToPractice themeToPractice;
    private boolean tick;
}
