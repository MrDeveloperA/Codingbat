package com.example.codingbat.repository;

import com.example.codingbat.entity.Condition;
import com.example.codingbat.entity.Done;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoneRepository extends JpaRepository<Done, Integer> {

}
