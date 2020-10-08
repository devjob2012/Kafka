package co.uk.deven.kafka.kafkaproject;

import co.uk.deven.kafka.kafkaproject.twitter.TwitterClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class KafkaProjectApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(KafkaProjectApplication.class, args);
        ApplicationContext applicationContext = SpringApplication.run(KafkaProjectApplication.class, args);
        System.out.println("Hello");
        TwitterClient client = applicationContext.getBean(TwitterClient.class);
        client.createClient();

    }
}
