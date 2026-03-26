package com.chatop.DTO;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class RentalCreateFormDTO {
    private String name;
    private float surface;
    private float price;
    private MultipartFile picture;
    private String description;
}