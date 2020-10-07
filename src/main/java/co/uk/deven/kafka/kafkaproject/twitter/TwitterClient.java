package co.uk.deven.kafka.kafkaproject.twitter;

import com.google.common.collect.Lists;
import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Client;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.Hosts;
import com.twitter.hbc.core.HttpHosts;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.event.Event;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class TwitterClient {

    public static final String CONSUMER_KEY = "0ZvLnvbtZ9eA7kh2VJcjS3eOL";
    public static final String CONSUMER_SECRET = "A5dwdAmgw96Cgy1kMRLNvNxbJUyVu9XjtsBekbANOiMmxAptzn";
    public static final String TOKEN = "3403937855-YAOn4Y4Bcss0H8xV7WqxbT7UYWcCxPbdCArAH8L";
    public static final String SECRET = "aYn6uORA3OcUPpEdOcEa6H9dxf7S7qGmCyV71YfxztKUB";


    public static void main(String[] args) {
        TwitterClient client = new TwitterClient();
        client.createClient();
    }

    public static void createClient() {
        BlockingQueue<String> msgQueue = new LinkedBlockingQueue<String>(100000);

        /** Declare the host you want to connect to, the endpoint, and authentication (basic auth or oauth) */
        Hosts hosebirdHosts = new HttpHosts(Constants.STREAM_HOST);
        StatusesFilterEndpoint hosebirdEndpoint = new StatusesFilterEndpoint();

        List<String> terms = Lists.newArrayList("MODI");
        hosebirdEndpoint.trackTerms(terms);

        // These secrets should be read from a config file
        Authentication hosebirdAuth = new OAuth1(CONSUMER_KEY, CONSUMER_SECRET, TOKEN, SECRET);


        ClientBuilder builder = new ClientBuilder()
                .name("Hosebird-Client-01")                              // optional: mainly for the logs
                .hosts(hosebirdHosts)
                .authentication(hosebirdAuth)
                .endpoint(hosebirdEndpoint)
                .processor(new StringDelimitedProcessor(msgQueue));

        Client hosebirdClient = builder.build();
// Attempts to establish a connection.
        hosebirdClient.connect();
        System.out.println("-------- " + msgQueue);
        // on a different thread, or multiple different threads....
        while (!hosebirdClient.isDone()) {
            try {
                String msg = msgQueue.poll(5, TimeUnit.SECONDS);
                System.out.println("Message is " + msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
