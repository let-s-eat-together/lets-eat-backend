package com.example.letseat.plan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {
    @Modifying
    @Query("UPDATE Plan e SET e.met = true WHERE e.id = :id")
    int setMetTrueById(@Param("id") Long id);

}
