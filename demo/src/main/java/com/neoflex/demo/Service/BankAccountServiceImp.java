package com.neoflex.demo.Service;

import com.neoflex.demo.Model.BankAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.UUID;


@Service
public class BankAccountServiceImp implements BankAccountService {
    private final BankAccount bankAccount;
    private static final String path = "D:/Neoflex/demo/src/main/resources/NamesForBankAccounts/"; //Путь к файлам с ФИО
    private List<String> listMansNames = Files.readAllLines(Paths.get(path + "MansNames.txt"));
    private List<String> listMansSurnames = Files.readAllLines(Paths.get(path + "MansSurnames.txt"));
    private List<String> listMansPatronymics = Files.readAllLines(Paths.get(path + "MansPatronymics.txt"));
    private List<String> listWomansNames = Files.readAllLines(Paths.get(path + "WomansNames.txt"));
    private List<String> listWomansSurnames = Files.readAllLines(Paths.get(path + "WomansSurnames.txt"));
    private List<String> listWomansPatronymics = Files.readAllLines(Paths.get(path + "WomansPatronymics.txt"));

    @Autowired
    public BankAccountServiceImp(BankAccount bankAccount) throws IOException {
        this.bankAccount = bankAccount;

    }

    @Override
    public BankAccount getAccount() {
        Random random = new Random();
        int[] gender = {0, 1};

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

    @Override
    public String toString() {
        return "BankAccountServiceImp{" +
                "bankAccount=" + bankAccount +
                '}';
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
