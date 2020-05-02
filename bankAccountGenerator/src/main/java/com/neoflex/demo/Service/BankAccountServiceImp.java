package com.neoflex.demo.Service;

import com.neoflex.demo.Model.BankAccount;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

import static com.neoflex.demo.utils.DataCache.*;


@Service
public class BankAccountServiceImp implements BankAccountService {

    @Override
    public BankAccount getAccount() {

        Random random = new Random();
        int[] gender = {0, 1};

        BankAccount bankAccount = new BankAccount();

        bankAccount.setUuid(UUID.randomUUID());

        if (gender[random.nextInt(gender.length)] == 1) {
            bankAccount.setFirstName(listMansNames.get(random.nextInt(listMansNames.size())));
            bankAccount.setLastName(listMansSurnames.get(random.nextInt(listMansSurnames.size())));
            bankAccount.setPatronymic(listMansPatronymics.get(random.nextInt(listMansPatronymics.size())));
        } else
            bankAccount.setFirstName(listWomansNames.get(random.nextInt(listWomansNames.size())));
        bankAccount.setLastName(listWomansSurnames.get(random.nextInt(listWomansSurnames.size())));
        bankAccount.setPatronymic(listWomansPatronymics.get(random.nextInt(listWomansPatronymics.size())));

        return bankAccount;
    }
}
