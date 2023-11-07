package com.sharshag;

import java.util.*;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;

public class Consumer {
    private static final String QUOTE_FEEDBACK = "quote-feedback";

    public static void main(String[] args) {
        
        String consumerGroup = "sampleconsumergroup";
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "localhost:9092");
        properties.setProperty("key.deserializer", IntegerDeserializer.class.getName());
        properties.setProperty("value.deserializer", StringDeserializer.class.getName());
        properties.setProperty("group.id", consumerGroup);
        properties.setProperty("client.id", Consumer.class.getName());

        KafkaConsumer<Integer, String> consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(List.of(QUOTE_FEEDBACK));

        while (true) {
             ConsumerRecords<Integer, String> records = consumer.poll(Duration.ofMillis(100));
             for (ConsumerRecord<Integer, String> record : records) {
                 System.out.println("Message key:" + record.key() + ", Message value:" + record.value()
                    + ", partition:" + record.partition() + ", offset:" + record.offset());
             }
            //  for (var record : records) {
            //     System.out.println("Message key:" + record.key() + ", Message value:" + record.value()
            //         + ", partition:" + record.partition() + ", offset:" + record.offset());
            //  }
           
        }

    }
}
