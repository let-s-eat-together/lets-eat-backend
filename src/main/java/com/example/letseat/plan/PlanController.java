package com.example.letseat.plan;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class PlanController {
    private final PlanService planService;
}
