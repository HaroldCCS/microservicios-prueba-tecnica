package com.aldeamo.publisher.queueBroker.publisher;

import com.aldeamo.publisher.dto.RequestPublishMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class MessageBrokerPublisher {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageBrokerPublisher.class);

    private RabbitTemplate rabbitTemplate;

    public MessageBrokerPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(RequestPublishMessage message) {
        LOGGER.info("Sending JSON message: {}", message.toString());

        Instant now = Instant.now();
        LOGGER.info("Hora de envio: {}", now.toString());
        MessagePostProcessor messagePostProcessor = msg -> {
            msg.getMessageProperties().getHeaders().put("timestamp", now);
            return msg;
        };

        rabbitTemplate.convertAndSend(exchange, routingKey, message, messagePostProcessor);
    }
}
