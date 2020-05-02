package com.neoflex.demo.Controller;

import com.neoflex.demo.Model.BankAccount;
import com.neoflex.demo.Service.BankAccountServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api", produces = APPLICATION_JSON_VALUE)
public class BankAccountGenerator {

    private final BankAccountServiceImp bankAccountServiceImp;

    @Autowired
    public BankAccountGenerator(BankAccountServiceImp bankAccountServiceImp) {
        this.bankAccountServiceImp = bankAccountServiceImp;
    }

    @GetMapping(value = "/getAccount")
    public BankAccount getBankAccount () {
        return bankAccountServiceImp.getAccount();
    }
}
