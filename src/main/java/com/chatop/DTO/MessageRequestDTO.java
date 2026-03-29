package com.chatop.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MessageRequestDTO {
    private Integer user_id;
    @NotNull(message = "Informations manquantes")
    private Integer rental_id;
    @NotBlank(message = "Informations manquantes")
    private String message;
}
