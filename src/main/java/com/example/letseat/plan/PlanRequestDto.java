package com.example.letseat.plan;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class PlanRequestDto {
    private Long sender_id;
    private LocalDate expiration_date;
    private String url;
}
