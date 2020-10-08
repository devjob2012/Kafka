package co.uk.deven.kafka.kafkaproject.producer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class ProducerDemo {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    private static final String TOPIC = "TWITTER_PRO";

    public void produce(BlockingQueue<String> msgQueue) {

        System.out.println("-------- " + msgQueue);

    }
}
