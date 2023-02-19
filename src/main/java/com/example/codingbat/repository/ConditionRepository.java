package com.example.codingbat.repository;

import com.example.codingbat.entity.CodeHelpVideo;
import com.example.codingbat.entity.Condition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConditionRepository extends JpaRepository<Condition, Integer> {

}
