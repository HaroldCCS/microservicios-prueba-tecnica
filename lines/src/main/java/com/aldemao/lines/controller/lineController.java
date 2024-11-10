package com.aldemao.lines.controller;

import com.aldemao.lines.dto.ApiResponse;
import com.aldemao.lines.service.LineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/line")
public class lineController {

    @Autowired
    private LineService lineService;

    @GetMapping("/validate-existence")
    public ResponseEntity<ApiResponse<Boolean>> validateExistence(@RequestParam String line) {
        boolean exists = lineService.validateExistence(line);

        String msseage = exists ? "exists" : "does not exist";
        ApiResponse<Boolean> response = new ApiResponse<Boolean>(200, msseage, exists);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/validate-authorization")
    public ResponseEntity<ApiResponse<Boolean>> validateAuthorization(@RequestParam String line) {
        boolean authorized = lineService.validateAuthorization(line);

        String msseage = authorized ? "Line autorhized" : "Line does not authorized";
        ApiResponse<Boolean> response = new ApiResponse<Boolean>(200, msseage, authorized);
        return ResponseEntity.ok(response);
    }
}
