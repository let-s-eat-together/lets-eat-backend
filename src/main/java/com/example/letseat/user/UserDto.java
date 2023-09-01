package com.example.letseat.user;

import com.example.letseat.plan.PlanDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class UserDto {
    private Long id;
    private String name;
    private String device_id;
    private List<PlanDto> plans;
}