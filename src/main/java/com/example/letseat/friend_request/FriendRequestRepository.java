package com.example.letseat.friend_request;


import com.example.letseat.sting.Sting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface FriendRequestRepository extends JpaRepository<Sting, Long>{
}
