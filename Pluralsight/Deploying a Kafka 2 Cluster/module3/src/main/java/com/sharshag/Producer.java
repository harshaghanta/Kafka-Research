package com.sharshag;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class Producer {

    private static final String QUOTE_FEEDBACK = "quote-feedback";
    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "localhost:9092");
        props.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        ProducerRecord<String, String> r1 = new ProducerRecord<String,String>(QUOTE_FEEDBACK, "Booking A");
        ProducerRecord<String, String> r2 = new ProducerRecord<String,String>(QUOTE_FEEDBACK, "Booking B");
        ProducerRecord<String, String> r3 = new ProducerRecord<String,String>(QUOTE_FEEDBACK, "Booking C");


        producer.send(r1);
        producer.send(r2);
        producer.send(r3);

        producer.flush();
        producer.close();

    }

}
