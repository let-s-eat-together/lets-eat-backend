package com.example.letseat.friend_request;

import com.example.letseat.friend_request.data.AlarmResponse;
import com.example.letseat.user.User;
import com.example.letseat.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendRequestService {
    final UserRepository userRepository;
    final FriendRequestRepository friendRequestRepository;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public void addRequest(Long user1_id, Long user2_id) {
        FriendRequest friendRequest = new FriendRequest();
        friendRequest.setUser1Id(user1_id);
        friendRequest.setUser2Id(user2_id);
        friendRequest.setCreationDate(LocalDate.now());
        friendRequestRepository.save(friendRequest);

    }

    public List<AlarmResponse> alarm(Long user2Id) {
        List<AlarmResponse> alarmResponses = new ArrayList<>();
        List<FriendRequest> alarmList = friendRequestRepository.findByUser2Id(user2Id);
        for(FriendRequest friendRequest : alarmList) {
            User user1 = userRepository.findNameById(friendRequest.getUser1Id()).orElseThrow();
            AlarmResponse alarmResponse = new AlarmResponse();
            alarmResponse.setRequestingUserId(friendRequest.getUser1Id());
            alarmResponse.setFriendName(user1.getUsername());
            alarmResponse.setCreationDate(friendRequest.getCreationDate().format(formatter));

            alarmResponses.add(alarmResponse);
        }
        return alarmResponses;
    }
}
