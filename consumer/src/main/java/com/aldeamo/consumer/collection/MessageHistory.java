package com.aldeamo.consumer.collection;

import com.aldeamo.consumer.dto.MessageType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

@Data
@Document(collection = "messages")
@NoArgsConstructor
@AllArgsConstructor
public class MessageHistory {

    @Id
    private String id;
    private String origin;
    private String destination;
    private MessageType messageType;
    private String content;
    private Long processingTime;

    @CreatedDate
    private LocalDateTime createdDate;
    private String error;
}
