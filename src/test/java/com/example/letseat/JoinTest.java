package com.example.letseat;

import com.example.letseat.plan.Plan;
import com.example.letseat.plan.PlanRepository;
import com.example.letseat.user.User;
import com.example.letseat.user.UserRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@SpringBootTest
public class JoinTest {
    @Autowired
    PlanRepository planRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager em;

    @Test
    @Transactional
    @Rollback(value = false)
    void join_test() {
        User user1 = new User();
        user1.setId(1L);
        user1.setName("User1");
        user1.setDeviceId("User1DeviceId");
        userRepository.save(user1);

        User user2 = new User();
        user2.setId(2L);
        user2.setName("User2");
        user2.setDeviceId("User2DeviceId");
        userRepository.save(user2);

        User user3 = new User();
        user3.setId(3L);
        user3.setName("User3");
        user3.setDeviceId("User3DeviceId");
        userRepository.save(user3);


        Plan plan1 = new Plan();
        plan1.setId(1L);
        plan1.setCreation_date(LocalDate.now());
        plan1.setExpiration_date(LocalDate.of(2023, 6, 2));
        planRepository.save(plan1);

        Plan plan2 = new Plan();
        plan2.setId(2L);
        plan2.setCreation_date(LocalDate.now());
        plan2.setExpiration_date(LocalDate.of(2023,8,3));
        planRepository.save(plan2);

        User savedUser1 = userRepository.findById(1L).get();
        User savedUser2 = userRepository.findById(2L).get();
        User savedUser3 = userRepository.findById(3L).get();
        Plan savedPlan1 = planRepository.findById(1L).get();
        Plan savedPlan2 = planRepository.findById(2L).get();
        savedPlan1.addUser(savedUser1);
        savedPlan1.addUser(savedUser2);
        savedPlan2.addUser(savedUser1);
        savedPlan2.addUser(savedUser3);
    }
}
