package com.project.logistics.models.dto;

import lombok.Data;

@Data
public class UserCreationDto {

    private String role;
    private String password;
    private String email;
    private String username;
    private String phoneNumber;
}
