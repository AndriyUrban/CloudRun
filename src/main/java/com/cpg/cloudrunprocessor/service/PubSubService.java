package com.cpg.cloudrunprocessor.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.TopicName;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;


@Service
public class PubSubService {

    private final Publisher publisher;

    @SneakyThrows
    public PubSubService(){
        this.publisher = Publisher
                .newBuilder(TopicName.of("cloudrun-json-processor", "pub-sub"))
                .build();
    }

    @SneakyThrows
    public void publishMessage(String message){
        ByteString data = ByteString.copyFromUtf8(message);
        PubsubMessage pubsubMessage = PubsubMessage.newBuilder().setData(data).build();
        ApiFuture<String> messageId = publisher.publish(pubsubMessage);
        System.out.println("Successfully published with ID-> " + messageId.get());
    }
}

