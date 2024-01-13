package com.example.letseat.sting.data;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StingDTO {
    private Long plan_id;
    private LocalDateTime creation_date;
    private String other_user_name;
    private Long countSting;
}
