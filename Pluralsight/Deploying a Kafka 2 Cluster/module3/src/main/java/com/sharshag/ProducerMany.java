package com.sharshag;

import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

public class ProducerMany {

    private static final String QUOTE_FEEDBACK = "quote-feedback";
    public static void main(String[] args) throws InterruptedException {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "localhost:9092");
        properties.setProperty("key.serializer", IntegerSerializer.class.getName());
        properties.setProperty("value.serializer", StringSerializer.class.getName());
        // properties.setProperty("batch.size", "0");
        properties.setProperty("linger.ms", "1000");
        properties.setProperty("compression.type", "gzip");

        KafkaProducer<Integer, String> producer = new KafkaProducer<>(properties);

        for (int i = 0; i < 5000; i++) {
            ProducerRecord<Integer,String> record = new ProducerRecord<Integer,String>(QUOTE_FEEDBACK, 
                ThreadLocalRandom.current().nextInt(1,1500), "Booking" + i);
            producer.send(record);
            Thread.sleep(10);
        }

        producer.flush();
        producer.close();
    }
}
