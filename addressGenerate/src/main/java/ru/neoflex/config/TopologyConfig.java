package ru.neoflex.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonSerde;
import ru.neoflex.model.Address;
import ru.neoflex.model.BankAccount;
import ru.neoflex.service.RandomAddressService;


@Configuration
@RequiredArgsConstructor
public class TopologyConfig {

    @Value("${kafka.inTopic}")
    private String inTopic;

    @Value("${kafka.outTopic}")
    private String outTopic;

    @Autowired
    private final RandomAddressService randomAddressService;
    @Bean
    public Topology createTopology(StreamsBuilder streamsBuilder) {
        KStream<String, BankAccount> input = streamsBuilder.stream(inTopic, Consumed.with(Serdes.String(),
                new JsonSerde<>(BankAccount.class)));

        KTable<String, BankAccount> tableBankAccount = input
                .filter((key, value) ->
                        value.getLastName().startsWith("А"))
                .groupByKey()
                .reduce((aggV, newV) -> newV);

        KTable<String, Address> tableAddress = tableBankAccount.mapValues(x -> randomAddressService.getAddress().block());
        tableAddress.toStream().to(outTopic, Produced.with(Serdes.String(), new JsonSerde<>(Address.class)));

        tableBankAccount.toStream().print(Printed.<String, BankAccount>toSysOut().withLabel("BankAccount"));//для отображения в консоле. при нужде раскоментить
        tableAddress.toStream().print(Printed.<String, Address>toSysOut().withLabel("Address"));//для отображения в консоле. при нужде раскоментить

        Topology topology = streamsBuilder.build();
        topology.describe();
        return topology;

    }
}
