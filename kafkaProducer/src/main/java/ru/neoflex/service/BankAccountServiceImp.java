package ru.neoflex.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.neoflex.model.AccountType;
import ru.neoflex.model.BankAccount;
import ru.neoflex.utils.DataCache;

@Service
@Slf4j
public class BankAccountServiceImp implements BankAccountService {

    @Value("${kafka.topic}")
    private String topic;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Scheduled(fixedDelay = 750)
    public void processAccount() {
        BankAccount bankAccount = getBankAccount();

        log.info("message from bankAccountGenerator {}", bankAccount);
        kafkaTemplate.send(topic, bankAccount.getUuid().toString(), bankAccount);
    }

    @Override
    public BankAccount getBankAccount() {
        RestTemplate restTemplate = new RestTemplate();

        BankAccount bankAccount = restTemplate.getForObject("http://localhost:8085/api/getAccount", BankAccount.class);

        assert bankAccount != null;
        if ((int) (Math.random() * 2) == 1) {
            bankAccount.setAccountType(new AccountType(DataCache.saving));
        } else {
            bankAccount.setAccountType(new AccountType(DataCache.current));
        }

        return bankAccount;
    }
}
