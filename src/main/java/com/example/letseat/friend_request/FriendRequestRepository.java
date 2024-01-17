package com.example.letseat.friend_request;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long>{
    List<FriendRequest> findByUser2Id(Long user2_id);
}
