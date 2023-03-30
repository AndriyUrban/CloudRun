package com.cpg.cloudrunprocessor.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.TopicName;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Service
public class PubSubService {

    private final Publisher publisher;

    public PubSubService() throws IOException {
        this.publisher = Publisher.newBuilder(TopicName.of("cloudrun-json-processor", "pub-sub")).build();
    }

    public void publishMessage(String message) throws InterruptedException, ExecutionException {
        ByteString data = ByteString.copyFromUtf8(message);
        PubsubMessage pubsubMessage = PubsubMessage.newBuilder().setData(data).build();
        ApiFuture<String> messageId = publisher.publish(pubsubMessage);
        System.out.println("Published message with ID: " + messageId.get());
    }
}

