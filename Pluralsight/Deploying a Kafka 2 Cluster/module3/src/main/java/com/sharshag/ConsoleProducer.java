package com.sharshag;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

public class ConsoleProducer {
    private static final String QUOTE_FEEDBACK = "quote-feedback";
    public static void main(String[] args) throws IOException {
        System.out.println("Enter message to be send and press <Enter>");
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "localhost:9092");
        properties.setProperty("key.serializer", IntegerSerializer.class.getName());
        properties.setProperty("value.serializer", StringSerializer.class.getName());

        KafkaProducer<Integer, String> producer = new KafkaProducer<>(properties);

        while (true) {
            String input = reader.readLine();
            int counter = 0;
            ProducerRecord<Integer, String> record = new ProducerRecord<Integer,String>(QUOTE_FEEDBACK, ++counter  , input);
            producer.send(record);
        }        
    }
}
