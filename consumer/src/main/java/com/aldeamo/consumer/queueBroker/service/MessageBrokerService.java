package com.aldeamo.consumer.queueBroker.service;

import com.aldeamo.consumer.collection.MessageHistory;
import com.aldeamo.consumer.dto.MessageDTO;
import com.aldeamo.consumer.service.MessageHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
public class MessageBrokerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageBrokerService.class);

    @Autowired
    MessageHistoryService messageHistoryService;

    public void processMessageConsume(MessageDTO messageDTO, Instant timer) {
        MessageHistory messageHistory = new MessageHistory();

        messageHistory.setOrigin(messageDTO.getOrigin());
        messageHistory.setDestination(messageDTO.getDestination());
        messageHistory.setMessageType(messageDTO.getMessageType());
        messageHistory.setContent(messageDTO.getContent());

        //Calculate time of processing
        Instant now = Instant.now();
        messageHistory.setProcessingTime(Duration.between(timer, now).toMillis());

        boolean sendMessage = messageHistoryService.availableSendMessageByDestination(messageHistory.getDestination());

        if (sendMessage) {
            //Logic to send message pending...
            LOGGER.info("Login to send message pending...");
        } else {
            messageHistory.setError("Destination has reached the message limit in 24 hours");
        }

        try {

            messageHistoryService.saveMessageHistory(messageHistory);
        } catch (Exception e) {
            LOGGER.error("Error saving message history", e);
        }
    }
}
