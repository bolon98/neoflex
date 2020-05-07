package ru.neoflex.service;

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

    @Override
    @Scheduled(fixedDelay = 10000)
    public BankAccount getBankAccount() {
        RestTemplate restTemplate = new RestTemplate();

        BankAccount bankAccount = restTemplate.getForObject("http://localhost:8085/api/getAccount", BankAccount.class);

        if ((int) (Math.random() * 2) == 1) {
            assert bankAccount != null;
            bankAccount.setAccountType(new AccountType(DataCache.saving));
        } else {
            assert bankAccount != null;
            bankAccount.setAccountType(new AccountType(DataCache.current));
        }

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("batch.size", 65536);
        props.put("buffer.memory", 10000000);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        KafkaProducer<String, Object> producer = new KafkaProducer<>(props);
        try {
            producer.send(new ProducerRecord<>("Bank_accounts", bankAccount.getUuid().toString(),bankAccount)).get(); //.get() - синхронная отправка сообщения
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bankAccount;
    }
}
