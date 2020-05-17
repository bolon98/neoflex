package ru.neoflex.repository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import ru.neoflex.model.BankAccount;

import javax.annotation.PostConstruct;

@Repository
@AllArgsConstructor
@NoArgsConstructor
public class MessagePublisherImpl implements MessagePublisher {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private HashOperations<String, String, Object> operation;

    @PostConstruct
    public void initOperation(){
        this.operation=redisTemplate.opsForHash();
    }

    @Override
    public void send(BankAccount bankAccount) {
        this.operation.put("Accounts", bankAccount.getUuid().toString(), bankAccount);
    }
}
