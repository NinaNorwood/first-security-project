package com.example.backend.model;

import lombok.Data;

@Data
public class AppUserDTO {

    private String userName;
    private String passwordHash;
}
