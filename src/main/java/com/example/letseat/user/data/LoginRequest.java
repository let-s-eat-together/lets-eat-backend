package com.example.letseat.user.data;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;

}
