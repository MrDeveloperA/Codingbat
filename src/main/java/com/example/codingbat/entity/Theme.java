package com.example.codingbat.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Theme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String name;
    @OneToOne
    private TaskText taskText;
    @OneToOne
    private Task task;
    @OneToOne
    private Solution solution;
    @OneToOne
    private GivenCode givenCode;
    @OneToOne
    private Answer answer;
}