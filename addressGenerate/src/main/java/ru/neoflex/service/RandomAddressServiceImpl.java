package ru.neoflex.service;

import lombok.SneakyThrows;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.neoflex.model.Address;

@Service
public class RandomAddressServiceImpl implements RandomAddressService {

    private final WebClient webClient;

    @Autowired
    public RandomAddressServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("https://randomapi.com/api/")
                .build();
    }

    public Mono<Address> getAddress() {
        return webClient.get()
                .uri("518ecc3294bfea8c6def671aefc3711f")
                .retrieve()
                .bodyToMono(String.class)
                .flatMap(this::parseJsonToAddress);
    }

    @SneakyThrows
    private Mono<Address> parseJsonToAddress (String jsonAddress) {
        var jsonObject = new JSONObject(jsonAddress);
        var jsonArray = jsonObject.getJSONArray("results");
        var addressObj = jsonArray.getJSONObject(0);

        String street = addressObj.getJSONObject("Address").getString("street");
        String city = addressObj.getJSONObject("Address").getString("city");
        String state = addressObj.getJSONObject("Address").getString("state");

        return Mono.just(new Address(street, city, state)) ;
    }
}
