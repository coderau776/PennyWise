package com.atharva.pwise.repository;

import com.atharva.pwise.entity.BankAccountInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankAccountRepository extends JpaRepository<BankAccountInfo, Long> {
    Optional<BankAccountInfo> findByBankAccNumberAndUserUserId(String bankAccNumber, Long userId);
}
