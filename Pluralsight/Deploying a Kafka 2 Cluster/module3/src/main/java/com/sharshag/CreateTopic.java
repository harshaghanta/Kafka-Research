package com.sharshag;

import java.util.concurrent.ExecutionException;

public class CreateTopic {
    
    private static final String QUOTE_FEEDBACK = "quote-feedback";

    public static void main(String[] args) throws InterruptedException, ExecutionException {   

        TopicUtils.createTopic(QUOTE_FEEDBACK, 2, (short) 1);
        TopicUtils.printTopics();
    }

    
}
