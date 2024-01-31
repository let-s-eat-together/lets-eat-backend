package com.example.letseat.friend_relation;


import com.example.letseat.friend_request.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface FriendRelationRepository extends JpaRepository<FriendRelation, Long> {
    List<FriendRelation> findByUser1Id(Long user1_id);

    List<FriendRelation> findByUser2_Id(Long userId);

    List<FriendRelation> findByUser1IdOrUser2Id(Long userId, Long userId1);
}
