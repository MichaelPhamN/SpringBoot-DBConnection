package com.example.jpaoracle.repository;

import com.example.jpaoracle.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
}
