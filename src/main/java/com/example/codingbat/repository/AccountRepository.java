package com.example.codingbat.repository;

import com.example.codingbat.entity.About;
import com.example.codingbat.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
}
