package com.example.letseat.user.data;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RenameResponse {
    private String user_name;
}
