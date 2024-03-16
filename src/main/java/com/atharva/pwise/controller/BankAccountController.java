package com.atharva.pwise.controller;

import com.atharva.pwise.entity.BankAccountInfo;
import com.atharva.pwise.service.BankInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bank")
public class BankAccountController {
    @Autowired
    BankInfoService bankInfoService;

    @PostMapping("/addAccount")
    public ResponseEntity<BankAccountInfo> addBankAccount(@RequestParam Long userId, @RequestBody BankAccountInfo bankAccountInfo) {
        return new ResponseEntity<>(bankInfoService.addBankAccount(userId, bankAccountInfo), HttpStatus.CREATED);
    }
}
