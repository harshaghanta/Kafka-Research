package com.sharshag;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.admin.Admin;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

public class TopicUtils {

    private static final String QUOTE_FEEDBACK = "quote-feedback";

    public static void deleteAllTopics() throws InterruptedException, ExecutionException {
        Admin admin = getAdmin();
        Set<String> topics = admin.listTopics().names().get();

        admin.deleteTopics(topics);
        admin.close();
    }

    public static void deleteTopic(String topicName) {
        Admin admin = getAdmin();
        admin.deleteTopics(List.of(topicName));
        admin.close();
    }

    public static void createTopic(String topicName, int noofPartitions, short replicationFactor) throws InterruptedException, ExecutionException {
        System.out.println("Creating topic " + topicName );
        Admin admin = getAdmin();
        NewTopic topic = new NewTopic(topicName, 2, (short)1);
        admin.createTopics(List.of(topic)).all().get();
        admin.close();

    }

    public static void printTopics() throws InterruptedException, ExecutionException {
        var admin = getAdmin();
        Set<String> topicNames = admin.listTopics().names().get();
        System.out.println("Print topics:");
        for (String topic : topicNames) {
            System.out.println(topic);
        }
        System.out.println();
        admin.close();
    }

    public static void displayConsumerGroupOffsets(String consumerGroup) throws InterruptedException, ExecutionException {
        Admin admin = getAdmin();
        Map<TopicPartition, OffsetAndMetadata> partitionOffsetMap = admin.listConsumerGroupOffsets(consumerGroup).partitionsToOffsetAndMetadata().get();
        partitionOffsetMap.forEach((partition, offsetdata) -> {
            System.out.println("Partition:" + partition + ",Offset:" + offsetdata.offset());
        });
        admin.close();
    }
    

    private static Admin getAdmin() {
        Admin admin = Admin.create(Map.of("bootstrap.servers", "localhost:9092"));
        return admin;
    }
    
}
