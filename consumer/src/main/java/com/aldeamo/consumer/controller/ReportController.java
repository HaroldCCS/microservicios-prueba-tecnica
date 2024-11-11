package com.aldeamo.consumer.controller;

import com.aldeamo.consumer.collection.MessageHistory;
import com.aldeamo.consumer.dto.ApiResponse;
import com.aldeamo.consumer.service.MessageHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/report")
public class ReportController {

    @Autowired
    MessageHistoryService messageHistoryService;

    @GetMapping("/destination")
    public ResponseEntity<ApiResponse<Page<MessageHistory>>> destinationReport(
            @RequestParam String line,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        Page<MessageHistory> data = messageHistoryService.getMessagesByDestination(line, page, size);
        ApiResponse<Page<MessageHistory>> response = new ApiResponse<>(200, "success", data);
        return ResponseEntity.ok(response);
    }
}
