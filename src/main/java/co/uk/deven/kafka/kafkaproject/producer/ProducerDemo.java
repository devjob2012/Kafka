package co.uk.deven.kafka.kafkaproject.producer;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

import java.util.Arrays;

public class ProducerDemo {
    public void produce() {
        KafkaProperties.Producer prod = new KafkaProperties.Producer();
        prod.setBootstrapServers(Arrays.asList("localhost:9092"));

    }
}
