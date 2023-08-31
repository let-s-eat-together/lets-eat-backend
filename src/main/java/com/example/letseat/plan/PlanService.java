package com.example.letseat.plan;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PlanService {
    private final PlanRepository planRepository;

    public PlanDto convertDto(Plan plan) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return PlanDto.builder()
                .id(plan.getId())
                .creation_date(plan.getCreation_date().format(formatter))
                .expiration_date(plan.getExpiration_date().format(formatter))
                .build();
    }

    public Optional<Plan> findPlanById(Long id) {
        return planRepository.findById(id);
    }
}
