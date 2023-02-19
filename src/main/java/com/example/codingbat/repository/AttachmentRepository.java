package com.example.codingbat.repository;

import com.example.codingbat.entity.About;
import com.example.codingbat.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {
}
