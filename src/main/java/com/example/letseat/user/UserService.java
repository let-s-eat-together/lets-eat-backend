package com.example.letseat.user;

import com.example.letseat.plan.Plan;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<Plan> findPlanByUserId(Long id) {
        User findUser = userRepository.findById(id).orElse(null);
        if (findUser != null) {
            return findUser.getPlans();
        }
        return Collections.emptyList();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Transactional
    public Long join(User user) {
        userRepository.save(user);
        return user.getId();
    }


}
