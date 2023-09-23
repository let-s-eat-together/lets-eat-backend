package com.example.letseat.plan.data;

import lombok.Data;

import java.time.LocalDate;
@Data
public class QrRequest {
    private LocalDate expiration_date;
}
