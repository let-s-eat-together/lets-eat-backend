package com.example.letseat.user;

import com.example.letseat.plan.Plan;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String device_id;

    @ManyToMany
    @JoinTable(name = "user_plan",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "plan_id"))
    private List<Plan> plans = new ArrayList<>();

    public void addPlan(Plan plan) {
        plans.add(plan);
    }
}
