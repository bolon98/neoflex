package ru.neoflex.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.neoflex.model.BankAccountInfo;
import ru.neoflex.repository.MessagePublisher;


@Service
@Slf4j
public class BankAccountServiceImp implements BankAccountService {

    private final MessagePublisher messagePublisher;

    @Autowired
    public BankAccountServiceImp(MessagePublisher messagePublisher) {
        this.messagePublisher = messagePublisher;
    }

    @Override
    @KafkaListener(topics = "${kafka.bankAccInfoTopic}")
    public void getBankAccountInfo(BankAccountInfo bankAccountInfo) {

        messagePublisher.send(bankAccountInfo);
        log.info("message from kafkaConsumer2 {}",bankAccountInfo);
    }
}
