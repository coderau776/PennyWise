package com.atharva.pwise.service;

import com.atharva.pwise.entity.BankAccountInfo;
import com.atharva.pwise.exception.ResourceNotFoundException;
import com.atharva.pwise.repository.BankAccountRepository;
import com.atharva.pwise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.atharva.pwise.utility.Constants.USER;
import static com.atharva.pwise.utility.Constants.USER_ID;

@Service
public class BankInfoService {
    @Autowired
    BankAccountRepository bankAccountRepository;
    @Autowired
    UserRepository userRepository;
    public BankAccountInfo addBankAccount(long userId, BankAccountInfo bankAccountInfo) {
        bankAccountInfo.setUser(userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(USER, USER_ID, userId)));
        return bankAccountRepository.save(bankAccountInfo);
    }
}
