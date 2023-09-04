package com.example.letseat.user;

import com.example.letseat.plan.PlanDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserDto {
    private Long id;
    private String name;
    private String device_id;
    private List<PlanDto> plans;
}