package com.aldeamo.consumer.repository;


import com.aldeamo.consumer.collection.MessageHistory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface MessageHistoryRepository extends MongoRepository<MessageHistory, String> {

    @Query("{ 'destination': ?0 }")
    List<MessageHistory> findMessagesByDestination(String destination);


    @Query("{ 'destination': ?0, 'createdDate': { $gte: ?1 } }")
    List<MessageHistory> findByDestinationAndCreatedDateAfter(String destination, LocalDateTime fromDate);

    @Query(value = "{ 'destination': ?0, 'createdDate': { $gte: ?1 } }", count = true)
    long countByDestinationAndCreatedDateAfter(String destination, LocalDateTime fromDate);
}