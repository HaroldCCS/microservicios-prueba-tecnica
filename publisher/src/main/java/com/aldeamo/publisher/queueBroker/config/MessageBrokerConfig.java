package com.aldeamo.publisher.queueBroker.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageBrokerConfig {
    @Value("${rabbitmq.queue.name}")
    private String queueJson;

    @Value("${rabbitmq.exchange.name}")
    private String exchangeJson;

    @Value("${rabbitmq.routing.key}")
    private String routingKeyJson;


    @Bean
    public Queue queueJson() {
        return new Queue(queueJson);
    }

    @Bean
    public TopicExchange exchangeJson() {
        return new TopicExchange(exchangeJson);
    }

    @Bean
    public Binding bindingJson() {
        return BindingBuilder
                .bind(queueJson())
                .to(exchangeJson())
                .with(routingKeyJson);
    }

    @Bean
    public MessageConverter converterJson() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converterJson());
        return rabbitTemplate;
    }
}
