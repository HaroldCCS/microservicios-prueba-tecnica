package com.aldeamo.consumer.dto;

import lombok.Data;

@Data
public class MessageDTO {

    private String origin;
    private String destination;
    private MessageType messageType;
    private String content;
}
