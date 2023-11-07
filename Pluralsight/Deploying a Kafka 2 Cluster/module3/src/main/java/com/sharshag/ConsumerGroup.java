package com.sharshag;

import java.util.concurrent.ExecutionException;

public class ConsumerGroup {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        TopicUtils.displayConsumerGroupOffsets("sampleconsumergroup");
    }
    
}
