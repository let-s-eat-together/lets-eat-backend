package com.example.letseat.friend_relation;


import com.example.letseat.auth.AuthMember;
import com.example.letseat.auth.argumentresolver.Auth;
import com.example.letseat.friend_relation.data.AcceptFriendBody;
import com.example.letseat.friend_relation.data.AcceptResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class FriendRelationController {
    final FriendRelationService friendRelationService;
    @PostMapping("/friend/accept")
    public void acceptFriend(@Auth AuthMember authMember, @RequestBody AcceptFriendBody acceptFriendBody){
        if(authMember==null){
            throw new RuntimeException("authMember가 없음");
        }
        Long userId = authMember.getId();
        friendRelationService.acceptFriend(userId, acceptFriendBody.getRequestingUserId());

    }
    @GetMapping("/friend/accept-alarm")
    public ResponseEntity<AcceptResponse> acceptAlarm(@Auth AuthMember authMember){
        if(authMember==null){
            throw  new RuntimeException("authMember가 없음");
        }
        Long userId = authMember.getId();
        return ResponseEntity.ok(friendRelationService.alarm(userId));
    }
    @GetMapping("/friend/get")
    public ResponseEntity<List<AcceptResponse>> getFriendList(@Auth AuthMember authMember){
        if(authMember==null){
            throw  new RuntimeException("authMember가 없음");
        }
        Long userId = authMember.getId();
        return ResponseEntity.ok(friendRelationService.getFriendList(userId));
    }

}
