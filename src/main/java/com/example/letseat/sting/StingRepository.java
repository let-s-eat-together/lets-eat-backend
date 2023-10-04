package com.example.letseat.sting;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StingRepository extends JpaRepository<Sting, Long> {
}
