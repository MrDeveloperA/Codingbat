package com.example.codingbat.repository;

import com.example.codingbat.entity.OuterTheme;
import com.example.codingbat.entity.Privacy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivacyRepository extends JpaRepository<Privacy, Integer> {

}

