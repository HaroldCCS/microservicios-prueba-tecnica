package com.aldeamo.publisher.service;

import com.aldeamo.publisher.dto.RequestPublishMessage;
import com.aldeamo.publisher.queueBroker.publisher.MessageBrokerPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublishService {

    @Autowired
    private LineService lineService;

    @Autowired
    private MessageBrokerPublisher queueMessagePublisher;


    public boolean publishMessage (RequestPublishMessage params) {
        Boolean result = lineService.validateAuthorization(params.getOrigin());

        if (result) {
            queueMessagePublisher.sendMessage(params);
        }
        return result;
    }

}
