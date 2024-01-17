package com.example.letseat.friend_relation;


import com.example.letseat.sting.Sting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface FriendRelationRepository extends JpaRepository<Sting, Long> {
}
