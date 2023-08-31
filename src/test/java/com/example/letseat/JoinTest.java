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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

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

        User user3 = new User();
        user3.setId(3L);
        user3.setName("User3");
        user3.setDevice_id("User3DeviceId");
        userRepository.save(user3);


        Plan plan1 = new Plan();
        plan1.setId(1L);
        plan1.setCreation_date(LocalDateTime.now());
        plan1.setExpiration_date(LocalDateTime.of(2023, 6, 2, 0, 0));
        planRepository.save(plan1);

        Plan plan2 = new Plan();
        plan2.setId(2L);
        plan2.setCreation_date(LocalDateTime.now());
        plan2.setExpiration_date(LocalDateTime.of(2023, 6, 1, 0, 0));
        planRepository.save(plan2);

        User savedUser1 = userRepository.findById(1L).get();
        User savedUser2 = userRepository.findById(2L).get();
        User savedUser3 = userRepository.findById(3L).get();
        Plan savedPlan1 = planRepository.findById(1L).get();
        Plan savedPlan2 = planRepository.findById(2L).get();
        savedUser1.addPlan(savedPlan1);
        savedUser2.addPlan(savedPlan1);
        savedUser1.addPlan(savedPlan2);
        savedUser3.addPlan(savedPlan2);
    }
}
