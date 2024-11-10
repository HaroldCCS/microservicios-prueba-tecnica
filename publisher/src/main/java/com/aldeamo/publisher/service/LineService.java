package com.aldeamo.publisher.service;

import com.aldeamo.publisher.dto.ApiResponse;
import com.aldeamo.publisher.exception.ValidateAuthorizedLineException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LineService {

    @Value("${microservice.lines.api.url}")
    private String lineApiUrl;

    @Value("${microservice.lines.api.key}")
    private String lineApiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public Boolean validateAuthorization(String line) {
        StringBuilder url = new StringBuilder(lineApiUrl);
        url.append("/api/v1/line/validate-authorization?line=");
        url.append(line);

        ParameterizedTypeReference<ApiResponse<Boolean>> responseType = new ParameterizedTypeReference<>() {};

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", lineApiKey);

        String requestBody = "";
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        ApiResponse<Boolean> response = restTemplate.exchange(
                url.toString(),
                HttpMethod.GET,
                entity,
                responseType
        ).getBody();

        if (response == null || response.getData() == false) {
            throw new ValidateAuthorizedLineException(400, response.getMessage());
        }

        return true;
    }
}
