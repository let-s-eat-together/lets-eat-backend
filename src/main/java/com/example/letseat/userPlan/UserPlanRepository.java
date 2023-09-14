package com.example.letseat.userPlan;

import com.example.letseat.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserPlanRepository extends JpaRepository<UserPlan, Long> {
    Optional<UserPlan> findByPlanIdAndUserIdNot(Long plan_id, Long user_id);

}
