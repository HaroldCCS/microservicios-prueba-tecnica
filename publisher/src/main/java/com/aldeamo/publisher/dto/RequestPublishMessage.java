package com.aldeamo.publisher.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RequestPublishMessage {

    @NotBlank(message = "Campo obligatorio")
    @NotNull(message = "El campo no puede ser nulo")
    private String origin;

    @NotBlank(message = "Campo obligatorio")
    @NotNull(message = "El campo no puede ser nulo")
    private String destination;

    @NotNull(message = "El campo no puede ser nulo")
    private MessageType messageType;

    @NotBlank(message = "Campo obligatorio")
    @NotNull(message = "El campo no puede ser nulo")
    private String content;
}
