package com.lucashthiele.backendtestifood.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

@Component
public class SqsMessageSenderService {
    private String queueUrl;

    @Autowired
    public SqsMessageSenderService(@Value("${aws.sqs.queue}") String queueUrl){
        this.queueUrl = queueUrl;
    }

    public void sendMessage(String message) {
        SendMessageRequest messageRequest = SendMessageRequest.builder()
                .queueUrl(queueUrl)
                .messageBody(message)
                .build();
        var sqsClient = SqsClient.builder().region(Region.US_EAST_1).build();
        sqsClient.sendMessage(messageRequest);
    }
}
