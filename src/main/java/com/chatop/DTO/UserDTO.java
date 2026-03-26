package com.chatop.DTO;

import java.sql.Date;

import lombok.Data;

@Data
public class UserDTO {
    private Integer id;
    private String email;
    private String name;
    private Date created_at;
    private Date updated_at;
}