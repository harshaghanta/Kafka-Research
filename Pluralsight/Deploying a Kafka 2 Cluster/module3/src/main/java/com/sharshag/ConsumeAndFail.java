package com.sharshag;
import java.util.*;
import java.time.Duration;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

public class ConsumeAndFail {
    
    private static final String QUOTE_FEEDBACK = "quote-feedback";
    public static void main(String[] args) throws InterruptedException {
        String consumerGroup = "sampleconsumergroup";
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "localhost:9092");
        properties.setProperty("key.deserializer", IntegerDeserializer.class.getName());
        properties.setProperty("value.deserializer", StringDeserializer.class.getName());
        properties.setProperty("group.id", consumerGroup);
        //properties.setProperty("max.poll.interval.ms", "10000");
        // properties.setProperty("client.id", ConsumeAndFail.class.getName());

        KafkaConsumer<Integer, String> consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(List.of(QUOTE_FEEDBACK));

        var consumedMessages = 0;

        while (true) 
        {
            ConsumerRecords<Integer, String> records = consumer.poll(Duration.ofMillis(100));
            
            consumedMessages++;

            for(var record : records) {
                System.out.println("Record is " + record.value());
            }

            // if(consumedMessages > 4) {
            //     Thread.sleep(60000);
            //     System.out.println("Some problem");
            // }
        }

    }
}
