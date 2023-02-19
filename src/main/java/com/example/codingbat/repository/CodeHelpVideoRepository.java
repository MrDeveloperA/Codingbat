package com.example.codingbat.repository;

import com.example.codingbat.entity.About;
import com.example.codingbat.entity.CodeHelpVideo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeHelpVideoRepository extends JpaRepository<CodeHelpVideo, Integer> {
}
