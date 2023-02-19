package com.example.codingbat.repository;

import com.example.codingbat.entity.ProblemText;
import com.example.codingbat.entity.ProgrammingLanguage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgrammingLanguageRepository extends JpaRepository<ProgrammingLanguage, Integer> {

}

