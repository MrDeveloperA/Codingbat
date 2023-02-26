package com.example.codingbat.repository;

import com.example.codingbat.entity.TaskText;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskTextRepository extends JpaRepository<TaskText, Integer> {

}
