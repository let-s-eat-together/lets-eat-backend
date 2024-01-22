package com.example.letseat.user.data;

import lombok.Data;

@Data
public class SignUpRequest {
    private String id;
    private String password;
    private String username;

}
