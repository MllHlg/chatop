package com.chatop.DTO;

import lombok.Data;

@Data
public class RentalUpdateFormDTO {
    private String name;
    private float surface;
    private float price;
    private String description;
}