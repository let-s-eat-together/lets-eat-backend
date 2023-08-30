package com.example.letseat.user;

import com.example.letseat.plan.Plan;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String name;
    private String device_id;
    private List<Plan> plans;

}
