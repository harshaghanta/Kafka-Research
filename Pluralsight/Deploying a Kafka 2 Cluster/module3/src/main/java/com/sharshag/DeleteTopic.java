package com.sharshag;

import java.util.concurrent.ExecutionException;
public class DeleteTopic {
    private static final String QUOTE_FEEDBACK = "quote-feedback";

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        TopicUtils.deleteAllTopics();
        TopicUtils.printTopics();
    }
}
