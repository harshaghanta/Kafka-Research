package com.sharshag;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

public class ProduceSlowly {
    
    private static final String QUOTE_FEEDBACK = "quote-feedback";
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "localhost:9092");
        properties.setProperty("key.serializer",IntegerSerializer.class.getName());
        properties.setProperty("value.serializer", StringSerializer.class.getName());

        KafkaProducer<Integer, String> producer = new KafkaProducer<>(properties);

        ProducerRecord<Integer, String> m1 = new ProducerRecord<Integer,String>(QUOTE_FEEDBACK, 1, "Message 1");
        ProducerRecord<Integer, String> m2 = new ProducerRecord<Integer,String>(QUOTE_FEEDBACK, 2, "Message 2");
        RecordMetadata m1Metadata = producer.send(m1).get();
        System.out.println("Message 1 key is:" + m1.key() + ", partition:" + m1Metadata.partition());
        
        Thread.sleep(30000);

        RecordMetadata m2Metadata = producer.send(m2).get();
        System.out.println("Message 1 key is:" + m2.key() + ", partition:" + m2Metadata.partition());

        producer.flush();
        producer.close();


    }
}
