package com.example.letseat;

import com.example.letseat.plan.Plan;
import com.example.letseat.plan.PlanRepository;
import com.example.letseat.user.User;
import com.example.letseat.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class JoinTest {
    @Autowired
    PlanRepository planRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    void join_test() {
        User user1 = new User();
        user1.setId(1L);
        user1.setName("User1");
        user1.setDevice_id("User1DeviceId");
        userRepository.save(user1);

        User user2 = new User();
        user2.setId(2L);
        user2.setName("User2");
        user2.setDevice_id("User2DeviceId");
        userRepository.save(user2);

        Plan plan = new Plan();
        plan.setId(1L);
        planRepository.save(plan);

        User savedUser1 = userRepository.findById(1L).get();
        User savedUser2 = userRepository.findById(2L).get();
        Plan savedPlan = planRepository.findById(1L).get();
        savedPlan.addUser(savedUser1);
        savedPlan.addUser(savedUser2);
        savedUser1.addPlan(savedPlan);
        savedUser2.addPlan(savedPlan);
    }
}
