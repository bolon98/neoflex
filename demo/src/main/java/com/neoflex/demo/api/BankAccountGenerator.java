package com.neoflex.demo.api;

import com.neoflex.demo.Model.BankAccount;
import com.neoflex.demo.Service.BankAccountService;
import com.neoflex.demo.Service.BankAccountServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class BankAccountGenerator {

    private final BankAccountService bankAccountService;

    @GetMapping(value = "/getAccount")
    public BankAccount getBankAccount () {
        return bankAccountService.getAccount();
    }
}
