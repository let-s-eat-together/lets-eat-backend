package com.example.letseat.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String ID_email);

    Optional<User> findById(Long userId);
    Optional<User> findNameById(@Param("userId") Long userId);

}
