package com.aldeamo.consumer.controller;

import com.aldeamo.consumer.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/report")
public class ReportController {


    @GetMapping("/destination")
    public ResponseEntity<ApiResponse<Object>> destinationReport(@RequestParam String line) {

        ApiResponse<Object> response = new ApiResponse<>(200, "exitoso", null);
        return ResponseEntity.ok(response);
    }
}
