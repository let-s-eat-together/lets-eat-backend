package com.example.letseat.plan.data;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PlanRequest {
    private LocalDate expired_date;
    private Long sender_id;
    private Long receiver_id;

}
