package ru.neoflex.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class BankAccountServiceImp implements BankAccountService {

    @Override
    @KafkaListener(topics = "${kafka.topic}")
    public void getBankAccount(Object bankAccount) {

        log.info("message from kafkaProducer {}",bankAccount);
    }
}
