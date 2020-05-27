package ru.neoflex.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonSerde;
import ru.neoflex.model.BankAccount;

@Configuration
@RequiredArgsConstructor
public class TopologyConfig {
    @Bean
    public Topology createTopology(StreamsBuilder streamsBuilder) {
        KStream<String, BankAccount> input = streamsBuilder.stream("Bank_accounts", Consumed.with(Serdes.String(),
                new JsonSerde<>(BankAccount.class)));


        Topology topology = streamsBuilder.build();
        System.out.println(topology.describe());
        return topology;

    }
}
