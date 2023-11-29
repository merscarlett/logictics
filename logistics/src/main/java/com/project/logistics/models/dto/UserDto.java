package com.project.logistics.models.dto;

import lombok.Data;

@Data
public class UserDto {

    private Long id;
    private String role;
    private String email;
    private String username;
    private String phoneNumber;
}
