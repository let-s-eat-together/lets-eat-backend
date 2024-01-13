package com.example.letseat.user;

import com.example.letseat.plan.Plan;
import com.example.letseat.userPlan.UserPlan;
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
    private String deviceId;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserPlan> userPlans = new ArrayList<>();


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserPlan> friend_relation = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserPlan> friend_request = new ArrayList<>();

    public void addPlan(Plan plan) {
        UserPlan userPlan = new UserPlan();
        userPlan.setPlan(plan);
        userPlan.setUser(this);
        plan.getUserPlans().add(userPlan);
        userPlans.add(userPlan);
    }

    public List<Plan> getPlans() {
        ArrayList<Plan> plans = new ArrayList<>();
        for (UserPlan userPlan : userPlans) {
            plans.add(userPlan.getPlan());
        }
        return plans;
    }
}

