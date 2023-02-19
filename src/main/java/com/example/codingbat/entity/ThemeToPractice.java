package com.example.codingbat.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.engine.spi.Resolution;
import org.hibernate.graph.Graph;

import java.awt.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class ThemeToPractice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private ProblemText problemText;
    @ManyToOne
    private Condition condition;
    @ManyToOne
    private Solution solution;
    @ManyToOne
    private GivenCode givenCode;
    @ManyToOne
    private EditCodeFromUser editCodeFromUser;
    private Integer fontSize;
    private boolean shorterOutput;

}
