package com.example.letseat.user.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ListResponse {
    private Long plan_id;
    private String creation_date;
    private String expiration_date;
    private String other_user_name;
}