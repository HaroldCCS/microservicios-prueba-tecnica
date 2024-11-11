package com.aldeamo.consumer.service;

import com.aldeamo.consumer.collection.MessageHistory;
import com.aldeamo.consumer.repository.MessageHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageHistoryService {

    @Value("${spring.messages.per.day.destination}")
    private int availableMessagesPerDayDestination;

    @Autowired
    private MessageHistoryRepository messageHistoryRepository;

    public Page<MessageHistory> getMessagesByDestination (String destination, int page, int size) {

        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable pageable = PageRequest.of(page > 0 ? page - 1 : 0, size, sort);
        return messageHistoryRepository.findMessagesByDestination(destination, pageable);
    }


    /**
     * Method to validate how many messages was sent in the last 24 hours to destination
     * If the destination has reached the limit of sends, return false
     */
    public boolean availableSendMessageByDestination (String destination) {
        LocalDateTime fromDate = LocalDateTime.now().minusHours(24);
        Long messagesSend = messageHistoryRepository.countByDestinationAndCreatedDateAfter(destination, fromDate);
        return messagesSend < availableMessagesPerDayDestination;
    }

    public MessageHistory saveMessageHistory (MessageHistory messageHistory) {
        return messageHistoryRepository.save(messageHistory);
    }
}
