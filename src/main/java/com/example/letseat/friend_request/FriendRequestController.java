package com.example.letseat.friend_request;


import com.example.letseat.auth.AuthMember;
import com.example.letseat.auth.argumentresolver.Auth;
import com.example.letseat.friend_request.data.AlarmResponse;
import com.example.letseat.friend_request.data.FriendRequestBody;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class FriendRequestController {
    final FriendRequestService friendRequestService;

    @PostMapping("/friend/request")
    public void addRequest(@Auth AuthMember authMember, @RequestBody FriendRequestBody friendRequestBody) {
        if(authMember==null){
            throw  new RuntimeException("authMember가 없음");
        }
        Long userId = authMember.getId();
        friendRequestService.addRequest(userId, friendRequestBody.getRequestedUserId());
    }

    @GetMapping("/friend/alarm")
    public ResponseEntity<List<AlarmResponse>> addAlarm(@Auth AuthMember authMember) {
        if(authMember==null){
            throw  new RuntimeException("authMember가 없음");
        }
        Long userId = authMember.getId();
        return ResponseEntity.ok(friendRequestService.alarm(userId));
    }

}
