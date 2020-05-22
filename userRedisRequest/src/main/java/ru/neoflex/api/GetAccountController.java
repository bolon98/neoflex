package ru.neoflex.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.model.BankAccount;
import ru.neoflex.repository.MessagePublisher;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class GetAccountController {

    private final MessagePublisher messagePublisher;

    @GetMapping(value = "/getAccount/{id}")
    public BankAccount getBankAccountId (@PathVariable(value = "id") String id) {
        return messagePublisher.get(id);
    }
}
