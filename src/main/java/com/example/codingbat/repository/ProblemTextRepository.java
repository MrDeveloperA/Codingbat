package com.example.codingbat.repository;

import com.example.codingbat.entity.Privacy;
import com.example.codingbat.entity.ProblemText;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemTextRepository extends JpaRepository<ProblemText, Integer> {

}

