package ru.neoflex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.neoflex.model.AccountType;
import ru.neoflex.model.BankAccount;
import ru.neoflex.utils.DataCache;

import java.util.Properties;

@Service
public class BankAccountServiceImp implements BankAccountService {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    @Scheduled(fixedDelay = 10000)
    public BankAccount getBankAccount() {
        RestTemplate restTemplate = new RestTemplate();

        BankAccount bankAccount = restTemplate.getForObject("http://localhost:8085/api/getAccount", BankAccount.class);

        assert bankAccount != null;
        if ((int) (Math.random() * 2) == 1) {
            bankAccount.setAccountType(new AccountType(DataCache.saving));
        } else {
            bankAccount.setAccountType(new AccountType(DataCache.current));
        }

        kafkaTemplate.send("Bank_accounts", bankAccount.getUuid().toString(), bankAccount.toString());

        return bankAccount;
    }
}
