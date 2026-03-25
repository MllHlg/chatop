package com.chatop.DTO;

import java.sql.Date;

import lombok.Data;

@Data
public class UserCreateDTO {
    private String name;
    private String email;
    private String password;
    private Date created_at = new Date(System.currentTimeMillis());
    private Date updated_at = new Date(System.currentTimeMillis());
}