package ru.neoflex.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.neoflex.model.BankAccount;
import ru.neoflex.repository.MessagePublisher;


@Service
@Slf4j
public class BankAccountServiceImp implements BankAccountService {

    private final MessagePublisher mp;

    @Autowired
    public BankAccountServiceImp(MessagePublisher mp) {
        this.mp = mp;
    }

    @Override
    @KafkaListener(topics = "${kafka.topic}")
    public void getBankAccount(BankAccount bankAccount) {

        mp.send(bankAccount);
        log.info("message from kafkaProducer {}",bankAccount);
//        System.out.println(mp.get(bankAccount.getUuid().toString()));
//        mp.get(bankAccount.getUuid().toString());для след мкс
    }
}
