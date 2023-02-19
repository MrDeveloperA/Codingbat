package com.example.codingbat.repository;

import com.example.codingbat.entity.GivenCode;
import com.example.codingbat.entity.Help;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HelpRepository extends JpaRepository<Help, Integer> {

}

