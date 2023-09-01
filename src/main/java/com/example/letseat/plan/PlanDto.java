package com.example.letseat.plan;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PlanDto {
    private Long id;
    private String creation_date;
    private String expiration_date;
    private String otherUserName;
}