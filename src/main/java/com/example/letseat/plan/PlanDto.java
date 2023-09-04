package com.example.letseat.plan;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlanDto {
    private Long id;
    private String creation_date;
    private String expiration_date;
    private String otherUserName;
}