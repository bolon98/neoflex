package ru.neoflex.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonSerde;
import ru.neoflex.model.BankAccount;


@Configuration
@RequiredArgsConstructor
public class TopologyConfig {

    @Value("${kafka.inTopic}")
    private String inTopic;

    @Value("${kafka.outTopic}")
    private String outTopic;

    @Bean
    public Topology createTopology(StreamsBuilder streamsBuilder) {
        KStream<String, BankAccount> input = streamsBuilder.stream(inTopic, Consumed.with(Serdes.String(),
                new JsonSerde<>(BankAccount.class)));

        KTable<String, BankAccount> table1 = input
                .filter((key,value) ->
                        value.getLastName().startsWith("А"))
                .groupByKey()
                .reduce((aggV,newV) -> newV);

         table1.toStream().to(outTopic, Produced.with(Serdes.String(), new JsonSerde<>(BankAccount.class)));

         table1.toStream().print(Printed.<String, BankAccount>toSysOut().withLabel("Bank Accounts")); //временно, для отображения

        Topology topology = streamsBuilder.build();
        topology.describe();
        return topology;

    }
}
