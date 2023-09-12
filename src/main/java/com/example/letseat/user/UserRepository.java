package com.example.letseat.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    Optional<User> findByDeviceId(String device_id);
//    String findNameById(@Param("userId") Long userId);

}
