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
import ru.neoflex.model.BankAccountInfo;

@Configuration
@RequiredArgsConstructor
public class TopologyConfig {

    @Value("${kafka.bankAccTopic}")
    private String bankAccTopic;

    @Value("${kafka.addressTopic}")
    private String addressTopic;

//    @Autowired
//    private final RandomAddressService randomAddressService;
    @Bean
    public Topology createTopology(StreamsBuilder streamsBuilder) {
        KStream<String, BankAccount> inputAcc = streamsBuilder.stream(bankAccTopic, Consumed.with(Serdes.String(),
                new JsonSerde<>(BankAccount.class)));

        KTable<String, BankAccount> tableBankAccount = inputAcc
                .groupByKey()
                .reduce((aggV, newV) -> newV);


        KStream<String, Address> inputAdd = streamsBuilder.stream(addressTopic, Consumed.with(Serdes.String(),
                new JsonSerde<>(Address.class)));

        KTable<String, Address> tableAddress = inputAdd.
                groupByKey()
                .reduce((aggV, newV) -> newV);

//        KTable<String, BankAccountInfo> joined = inputAcc.join(inputAdd, (acc, add) -> new BankAccountInfo(acc, add));

        tableBankAccount.toStream().print(Printed.<String, BankAccount>toSysOut().withLabel("BankAccount"));//для отображения в консоле. при нужде раскоментить
        tableAddress.toStream().print(Printed.<String, Address>toSysOut().withLabel("Address"));//для отображения в консоле. при нужде раскоментить

        Topology topology = streamsBuilder.build();
        topology.describe();
        return topology;

    }
}
