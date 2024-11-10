package com.aldeamo.consumer.queueBroker.consumer;

import com.aldeamo.consumer.dto.MessageDTO;
import com.aldeamo.consumer.queueBroker.service.MessageBrokerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;

@Service
public class MessageBrokerConsumer {

    @Autowired
    private MessageBrokerService messageBrokerService;

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageBrokerConsumer.class);


    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void consume(Message message) {
        String body = new String(message.getBody());
        MessageDTO messageDTO = parseMessage(body);
        LOGGER.info("Consume JSON message: {} {}", messageDTO.toString(), getTimer(message).toString());

        messageBrokerService.processMessageConsume(messageDTO, getTimer(message));
    }


    /**
     * Method to get timestamp of message queue
     */
    private Instant getTimer (Message message) {
        Map<String, Object> headers = message.getMessageProperties().getHeaders();

        Object timestampHeader = headers.get("timestamp");
        Instant timer;

        if (timestampHeader instanceof String) {
            timer = Instant.parse((String) timestampHeader);
        } else {
            timer = Instant.now();
        }
        return timer;
    }


    /**
     * Method to parse message
     */
    private MessageDTO parseMessage(String body) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(body, MessageDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse message body: " + e.getMessage(), e);
        }
    }
}
