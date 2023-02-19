package com.example.codingbat.repository;

import com.example.codingbat.entity.InnerThemeWithVideo;
import com.example.codingbat.entity.Link;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository extends JpaRepository<Link, Integer> {

}

