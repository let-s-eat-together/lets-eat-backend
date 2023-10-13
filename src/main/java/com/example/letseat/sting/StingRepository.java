package com.example.letseat.sting;

import com.example.letseat.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StingRepository extends JpaRepository<Sting, Long> {

    Optional<Sting> findBySenderIdAndPlanId(Long senderId, Long planId);

    List<Sting> findByReceiverId(Long receiverId);
}
